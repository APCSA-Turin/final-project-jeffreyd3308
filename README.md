[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=19530507)

# Project Overview
This Java project takes in a city name and calls the OpenWeatherMap API using the name. With the API's data, the program displays temperature, precipatitation information, and more.

# Code Breakdown
## ```main(String[] args)
- entry point of the program
- creates a JFrame and constructs an Input panel
- manages the input of the user
- calls the API depending on the input and rejects invalid city names

## ```cityString(String city)
- transforms the city string into a readable string by the API

## ```GradientPanel(Color start, Color end, LayoutManager layout)
- creates a custom JPanel with a gradient background

## ```createTempPredictions(JSONArray forecast)
- sorts 5 most recent forecast data from the API into a list
- creates a JPanel of all 5 forecast data
- displays temperature and time

## ```convertTime(String time)
- convert 24 hour formatted time into 12 hour format

## ```calculateTempAverage(ArrayList<Double> temperatures)
- calculates average of a list of temperatures

## GUI components
- uses JPanels and JFrames to display data

# Features Implemented
## Base Project 88%
- Connects to the OpenWeatherMap API
- Uses at least 3 classes(Display, DisplayPreview, Input)
- Parses JSON data using string matching
- outputs temperature, brief weather description, visibility, humidity, and more

## Basic Statistics 6%
- Finds the average of a list of temperatures

## GUI 2%
- Uses swing

## Filter and Sort Data 2%
- DisplayPreview class is constructed with JSONObjects

## Total 98%

# What I Learned
- learned how to connect to an API
- learned how to find data from JSON with a String key
- learned how to use specific objects in Swing
- learned how to utilize statistical methods such as averages 

