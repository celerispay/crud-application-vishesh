#! /bin/bash

echo "Sending request ..."

curl -u 'lily:$2a$10$aPu1RcOTV8NrLsmQD0FpUeILLjnOg6vJ4ZudatCpPmud7TFS3CD9G' http://localhost:8080/api/job/start/First%20Job
