echo ### Criando Bucket no S3 do LocalStack...
aws --endpoint http://localhost:4566 --profile local s3 mb s3://algafood