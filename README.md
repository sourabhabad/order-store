
# Order-Service

Springboot application to upload parse csv file and store in H2 Database




## Tech Stack

- Springboot - 3.0.7
- Java - 17
- H2 Database
- lombok
- common-csv


## API Reference

#### Get all items

```
  POST /store/start
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `file` | `File` | **Required**. CSV File to process |

## CURL

```
curl --location 'http://localhost:8081/store/start' \
--form 'file=@"/Users/home/Downloads/order_file.csv"'
```


## Documentation

- Clone project
- Set Java SDK 17
- Run application locally
- Download Excel file and convert in csv or use csv file present in main resource
- Execuite curl give valid file path in request
- On Successful response check data present in H2 database
#### *To check data in h2 database*
- Go to this url http://localhost:8081/h2-console
- Click connect with default entry (user:sa, password empty)
- execuite Select query before and after api ```SELECT * from orders;```


## Run Locally

To start this project run

```bash
  gradle bootRun --continuous
```
or run SpringBootApplication main method

