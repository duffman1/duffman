java -jar /Applications/Test/Selenium/selenium-server-standalone-2.41.0.jar -role webdriver -hub http://localhost:4445/grid/register -port 6001 -maxSession 1 -nodeTimeout 120 -Dwebdriver.chrome.driver=/Applications/Test/Selenium/chromedriver -browser "browserName=firefox,version=local,maxInstances=1,platform=MAC" -browser "browserName=chrome,version=local,maxInstances=1,platform=MAC" -browser "browserName=phantomjs,version=local,maxInstances=1,platform=MAC" -browser "browserName=htmlunit,version=local,maxInstances=1,platform=MAC" -browser "browserName=safari,version=local,maxInstances=1,platform=MAC" -browser "browserName=opera,version=local,maxInstances=1,platform=MAC"