# Contains Spring boot application with functionality according task

Consider that your system has an API that is called by electricity counters:

POST /counter_callback

{

"counter_id": "1","amount": 10000.123
}

To get information additional information about the counter you have to call the following external API:

GET /counter?id=1

{

<code>"id": "1","village_name": "Villarriba"</code>

}

As a result, it's expected that your system will expose the following API:

GET /consumption_report?duration=24h

{

"villages": [{        "village_name": "Villarriba",        "consumption": 12345.123    },    {        "village_name": "Villabajo",        "consumption": 23456.123    }]
}
