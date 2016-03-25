aws lambda create-function --function-name hello3 --runtime java8 --role arn:aws:iam::970497142439:role/breathe_lambdaexecutionrole_MOBILEHUB_1169060133 --handler com.edifecs.cdm.breathe.riskassesment.RiskAssessmentService::hello2 --zip-file fileb://C:/ecgit/breathe/aws-lambda/build/libs/aws-lambda-1.0-SNAPSHOT.jar

[--code <value>]
[--description <value>]
[--timeout <value>]
[--memory-size <value>]
[--publish | --no-publish]
[--vpc-config <value>]
[--zip-file <value>]
[--cli-input-json <value>]
[--generate-cli-skeleton]

aws lambda invoke --function-name sayhi --payload "{\"name\":\"Tridib\"}" hello.out
- Single quote in json input does not work