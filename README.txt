to execute programm run:
1) place jar file outside the target folder
2) run programm with parameters(parameter = "full\path\to\folder\with\files\")
	java -jar .\fileunion-1.0.jar "d:\Temp\Tests\employees\" "d:\Temp\Tests\products\" "d:\Temp\Tests\productlines\"
3) The result file will be near target folders in "output" folder
4) To run recursive manager employee links put
	java -jar .\fileunion-1.1.jar rec 0 6 "," "d:\Temp\Tests\employees\"
5) To fill NULL column by lookup column we need to run
Parameters: lookup <changeColumnIndex> <lookupColumnIndex> "delimiter" "change_column_full_FOLDER_path" "lookup_column_FILE_path"
	java -jar .\fileunion-1.2.jar lookup 1 0 "," "d:\Temp\Tests\orderdetails" "d:\Temp\Tests\output\products.csv" .
6) To fill randomly value in particular column(s), currently NULL should execute:
Parameters: null \"full_path_to_file\" \"delimiter\" [{<changeColumnIndex> <percent to make null>} - repeat n times if n columns needed]
	java -jar .\fileunion-1.3.jar null "d:\Temp\Tests\output\employees.txt" "," 6 20
7) To cut some amount of rows, depending on percent of invalid date:
Parameters: delete \"full_path_to_file\" \"percent_of_invalid_data\"
	java -jar .\fileunion-1.3.jar delete "D:\DGT\TDM\fileunion\data\dataProvidersIvalidData\payments.csv" 20
    in case of offices we need leave 100 minus percent_of_invalid_data rows and then concat the result with the offices valid file:
    1) java -jar .\fileunion-1.3.jar delete "D:\DGT\TDM\fileunion\data\dataProvidersIvalidData\officesInitial.csv" 80
    Then copy the result file officesInitial_output to a common folder with office.csv with valid data
    2) java -jar .\fileunion-1.3.jar "D:\DGT\TDM\fileunion\data\dataProvidersIvalidData\validAndAdditionalOffices"



	java -jar .\fileunion-1.3.jar lookup 1 0 "," "D:\DGT\TDM\fileunion\data\dataProviders\orderdetailsWithNull" "D:\DGT\TDM\fileunion\data\dataProviders\products.csv" .