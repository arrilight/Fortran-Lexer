# Fortran 90/95 Lexer
Made by BS3 students:
Pavel Kazantsev and Dzesov Georgy
# About the code:
## Structure
The code is just a simple maven java project, that can be opened in any IDE by importing the `pom.xml` file.
If dependecies don't install automatically: 
just open the `pom.xml` file in IntelliJ IDEA and press "Install" in the right corner
OR 
use the command `mvn install` in the parent directory.
### Run the program
To start the program, simply run `Main.main() ` in `/src/main/java`.
### Run the tests
To run the tests, simply run `TestName` in `/src/test/java/addhere` using IntelliJ IDEA or any other IDE that supports `JUnit`.
# Tokens
We decided to represent the tokens in a form of a simple class with 2 fields: `type` and `value`.
## Types
There are 5 basic token types that we decided to use:
1) Literal: It includes both string (`"string"`, `'another_string'`) and digit (`56.7`, `5f.23`, `576`) literals. 
2) Identifier: every string that is not incapsulated in "" or '', and is not a keyword is concidered to be an identifier.
3) Keywords: every keyword that the language is using, uncluding bulit-in types (such as `integer` or `character`). For the list of all keywords in the language you can refer to `keywords.txt` file in the parent directory of the project.
4) Separator: since the language uses new lines as separators, the only separators  we identified are `&` (line concatenation), `,`, `$`,  `(` and `)`.
5) Operators: this is the list of all operators that are used in the language:
  `=` – operator of assignment,
  `==` or `.eq.` – logical operator of equality,
  `=>` – pointer,
  `>` – logical "greater" or closing angular bracket,
  `>=` – logical "greater or equal",
  `<` – logical "less" or opening angular bracket,
  `<=` – logical "less or equal",
  `*` – asterisk,
  `**` – power,
  `-`, `+`, `*`, `/` – arithmetical operands,
  `:` – column,
  `::` – type specification symbol,
  `?` – request name specifier,
  `\` – escape character,
  `!` – symbol indicating the comments,
  
## Values
We decided to store string name of values in the value field just for the sake of readability and understandability. For the actual lexer these values would be reaplced with integer identifiers.
  
  
  
