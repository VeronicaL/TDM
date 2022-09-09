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
	