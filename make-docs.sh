#!/usr/bin/env bash

# Doc generator from: https://github.com/pseudomuto/protoc-gen-doc
# NOTE - the docs builder will not create links to top-level protos
#           correctly.  To fix, the docs/index.html file is sed'd to
#           `s/href="#([A-Z])/href="#.$1/g`

set -e

#docker pull pseudomuto/protoc-gen-doc

TMP_DIR=../build/tmp-docs-protos

SEDOPTION='-i'
if [[ "$OSTYPE" == "darwin"* ]]; then
  SEDOPTION=(-i '')
fi

function gen_doc() {
  docker run --rm -v "$(pwd)/docs":/out -v "$(pwd)"/${TMP_DIR}:/protos pseudomuto/protoc-gen-doc ${1} --doc_opt=/out/docgen/markdown.tmpl,${2}:validate/*
  sed "${SEDOPTION[@]}" -e "s|Protocol Documentation|${3}|" "docs/${2}"
  # Get rid of extraneous lines in TOC list that make format wonky
  sed "${SEDOPTION[@]}" -e '/^  $/d' "docs/${2}"
}

function gen_example() {
  (echo "Example:" && echo "\`\`\`json" && cat $1 && echo " " && echo "\`\`\`") > ${TMP_DIR}/example.md
  sed "${SEDOPTION[@]}" "/INSERT_EXAMPLE/r ${TMP_DIR}/example.md" $2
  sed "${SEDOPTION[@]}" 's/INSERT_EXAMPLE//' $2
}


mkdir -p ${TMP_DIR}
cp -r src/main/proto/* ${TMP_DIR}
cp -r build/extracted-include-protos/main/validate ${TMP_DIR}

# Generate top-level documentation
gen_doc "tech/figure/asset/v1beta1/asset.proto" "asset.md" "Asset (NFT)"
gen_doc "tech/figure/loan/v1beta1/loan.proto" "loan.md" "Loan"
gen_doc "tech/figure/loan/v1beta1/mortgage.proto" "mortgage.md" "Mortgage"
gen_doc "tech/figure/loan/v1beta1/heloc.proto" "heloc.md" "HELOC"
gen_doc "tech/figure/loan/v1beta1/heloan.proto" "heloan.md" "HELOAN"
gen_doc "tech/figure/loan/v1beta1/mismo_loan.proto" "mismo.md" "MISMO Loan"
gen_doc "io/dartinc/registry/v1beta1/registry.proto" "registry.md" "Digital Asset Registry Technology"

# Generate and organize versioned documentation
gen_doc "tech/figure/validation/v1beta1/validation.proto" "validation_v1beta1.md" "Loan Validation (v1beta1)"
mv -f "docs/validation_v1beta1.md" "docs/validation/v1beta1.md"
gen_doc "tech/figure/validation/v1beta2/validation.proto" "validation_v1beta2.md" "Loan Validation (v1beta2)"
mv -f "docs/validation_v1beta2.md" "docs/validation/v1beta2.md"

pushd ${TMP_DIR} > /dev/null
FILE_LIST=$( find tech/figure/servicing -name "*.proto" | sort -n)
popd > /dev/null
gen_doc "${FILE_LIST}" "servicing.md" "Loan Servicing"

pushd ${TMP_DIR} > /dev/null
FILE_LIST=$( find tech/figure/util -name "*.proto" | sort -n)
popd > /dev/null
gen_doc "${FILE_LIST}" "util.md" "Util"

sed "${SEDOPTION[@]}" -e 's/#tech.figure.util/util.md#tech.figure.util/g' docs/*.md
sed "${SEDOPTION[@]}" -e 's/#tech.figure.util/..\/util.md#tech.figure.util/g' docs/*/*.md

gen_example docs/docgen/examples/asset.json docs/asset.md
gen_example docs/docgen/examples/loan.json docs/loan.md
gen_example docs/docgen/examples/mismo.json docs/mismo.md
