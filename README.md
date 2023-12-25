#GALAXY MERCHANT TRADING GUIDE
Solution to the galaxy merchant trading problem

# Assumptions

Words are case senstitive

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

```
| Input                                                                | Query Type             | Handler                   |
| -------------------------------------------------------------------- | ---------------------  | ------------------------- |
| glob is I                                                            | Assign Query           | CurrencyHandler           |
| glob glob Silver is 34 Credits                                       | Trade commodity Query  | TradeHandler              |
| how much is pish tegj glob glob ?                                    | find query             | FindQueryHandler          |
| Does pish tegj glob glob Iron has more Credits than glob glob Gold ? | Compare Query          | ComparisonQueryHandler    |
| Is glob prok larger than pish pish?                                  | Compare Query          | ComparisonQueryHandler    |
```

## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)
