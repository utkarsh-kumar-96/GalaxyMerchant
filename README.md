#GALAXY MERCHANT TRADING GUIDE
Solution to the galaxy merchant trading problem

# Assumptions

Words are case senstitive

## UML Diagram
<img align="left" src='https://private-user-images.githubusercontent.com/25564403/292772526-3de0b54a-217b-49a9-8110-d6d2ff1c5963.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTEiLCJleHAiOjE3MDM1MjYxNjEsIm5iZiI6MTcwMzUyNTg2MSwicGF0aCI6Ii8yNTU2NDQwMy8yOTI3NzI1MjYtM2RlMGI1NGEtMjE3Yi00OWE5LTgxMTAtZDZkMmZmMWM1OTYzLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFJV05KWUFYNENTVkVINTNBJTJGMjAyMzEyMjUlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjMxMjI1VDE3Mzc0MVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWNiMmI0NDU0NDJjMTYwOTJkMzVjOGE3ZDAxMTEzYmEyYWUwYjlmNGM3OGE5MWRlZTE2ZjY1OWY4MGFkMGEwNTEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.CSeUMF7VYyxnFNtqqxrofd1zy87H2WCNmsE8PeFMM_8' alt='uml_diagram'>

## Design Patterns Used

Chain Of Responsibility

## How to Run

Pass your queries to execute method in `ParseQuery.java` file. The application will print output directly to the console.


## Application Terminology
- Input statements are referred as `Query`
- Input words like `Gold`, `Silver` or `iron` which are not denoted by Roman Numerals are referred to as `commodity` or `trade commodity`
- Input words like `glob`, `prok`, `pish` which are denoted by Roman Numerals in input statement are called `currency`

## Explanation

The program identifies the type of query using `regex` and passes it to the next `handler` until respective handler is found

| Input                                                                | Query Type             | Handler                   |
| -------------------------------------------------------------------- | ---------------------  | ------------------------- |
| glob is I                                                            | Assign Query           | CurrencyHandler           |
| glob glob Silver is 34 Credits                                       | Trade commodity Query  | TradeHandler              |
| how much is pish tegj glob glob ?                                    | find query             | FindQueryHandler          |
| Does pish tegj glob glob Iron has more Credits than glob glob Gold ? | Compare Query          | ComparisonQueryHandler    |
| Is glob prok larger than pish pish?                                  | Compare Query          | ComparisonQueryHandler    |

## Sample Input
```
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob glob Gold ?
how many Credits is glob glob glob glob glob glob Gold ?
how many Credits is pish tegj glob Iron ?
Does pish tegj glob glob Iron has more Credits than glob glob Gold ?
Does glob glob Gold has less Credits than pish tegj glob glob Iron?
Is glob prok larger than pish pish?
Istegj glob glob smaller than glob prok?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
```
## Sample Output 
```
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob glob Gold is 28900 Credits
Requested number is in invalid format
pish tegj glob Iron is 8015.5 Credits
pish tegj glob glob Iron has less Credits than glob glob Gold
glob glob Gold has more Credits than pish tegj glob glob Iron
glob prok is smaller than pish pish
tegj glob glob is larger than glob prok
I have no idea what you are talking about
```
## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/utkarsh-kumar26/)
