package com.test.calculator;

public class Calculator {

    Expression expression = new Expression();
    private String temp;

    public String calculation(String value){
        if(value.equals("clear")){
            expression.clearExpression();
            return expression.getExpression();
        }
        if(value.equals("=")){
            String sol = solution(expression.getExpression());
            expression.clearExpression();
            return sol;
        }
        String expression1 = expression.getExpression();
        if((expression1 != null) && (expression1.length() > 0)){
            temp = expression1.substring(expression1.length() - 1);
            if((temp.equals("+") && value.equals("+"))
                    || (temp.equals("-") && value.equals("-"))
                    || (temp.equals("*") && value.equals("*"))
                    || (temp.equals("/") && value.equals("/"))
                    || (temp.equals(".") && value.equals("."))
                    || (temp.equals("^") && value.equals("^"))
                    || (temp.equals("+") && value.equals("-"))
                    || (temp.equals("+") && value.equals("*"))
                    || (temp.equals("+") && value.equals("/"))
                    || (temp.equals("+") && value.equals("."))
                    || (temp.equals("+") && value.equals("^"))
                    || (temp.equals("-") && value.equals("+"))
                    || (temp.equals("-") && value.equals("*"))
                    || (temp.equals("-") && value.equals("/"))
                    || (temp.equals("-") && value.equals("."))
                    || (temp.equals("-") && value.equals("^"))
                    || (temp.equals("*") && value.equals("+"))
                    || (temp.equals("*") && value.equals("/"))
                    || (temp.equals("*") && value.equals("."))
                    || (temp.equals("*") && value.equals("^"))
                    || (temp.equals("/") && value.equals("+"))
                    || (temp.equals("/") && value.equals("*"))
                    || (temp.equals("/") && value.equals("."))
                    || (temp.equals("/") && value.equals("^"))
                    || (temp.equals(".") && value.equals("+"))
                    || (temp.equals(".") && value.equals("-"))
                    || (temp.equals(".") && value.equals("*"))
                    || (temp.equals(".") && value.equals("/"))
                    || (temp.equals(".") && value.equals("^"))
                    || (temp.equals("^") && value.equals("+"))
                    || (temp.equals("^") && value.equals("*"))
                    || (temp.equals("^") && value.equals("/"))
                    || (temp.equals("^") && value.equals("."))){
                return expression.getExpression();
            }
        }
        else if(value.equals("+")
                || value.equals("*")
                || value.equals("/")
                || value.equals("^")
                ||value.equals(".")){
            return expression.getExpression();
        }
        expression.saveExpression(value);
        return expression.getExpression();
    }
    public String solution(String value){
        String answer = "";
        double firstNumber;
        double lastNumber;
        String firstNumberS = "";
        String lastNumberS = "";
        String operation = "";
        char[] charArray = value.toCharArray();

        for(int i = 0; i < charArray.length; i++){
            if(!operation.equals("")){
                lastNumberS += String.valueOf(charArray[i]);
                continue;
            }
            if(i > 0){
                if(charArray[i] == '+'){
                    operation = "+";
                    continue;
                }
                else if(charArray[i] == '-'){
                    operation = "-";
                    continue;
                }
                else if(charArray[i] == '*'){
                    operation = "*";
                    continue;
                }
                else if(charArray[i] == '/'){
                    operation = "/";
                    continue;
                }
                else if(charArray[i] == '^'){
                    operation = "^";
                    continue;
                }
            }
            firstNumberS += String.valueOf(charArray[i]);
        }

        firstNumber = Double.parseDouble(firstNumberS);
        lastNumber = Double.parseDouble(lastNumberS);

        switch (operation){
            case "+":
                answer = plus(firstNumber, lastNumber);
                break;
            case "-":
                answer = minus(firstNumber, lastNumber);
                break;
            case "*":
                answer = multi(firstNumber, lastNumber);
                break;
            case "/":
                answer = divide(firstNumber, lastNumber);
                break;
            case "^":
                answer = pow(firstNumber, lastNumber);
                break;
        }
        return answer;
    }
    public String plus(double firstNumber, double lastNumber){
        double value = firstNumber + lastNumber;

        return Double.toString(value);
    }
    public String minus(double firstNumber, double lastNumber){
        double value = firstNumber - lastNumber;

        return Double.toString(value);
    }
    public String multi(double firstNumber, double lastNumber){
        double value = firstNumber * lastNumber;

        return Double.toString(value);
    }
    public String divide(double firstNumber, double lastNumber){
        double value = firstNumber / lastNumber;
        if(Double.toString(value) == "Infinity"){
            return "деление на ноль";
        }
        return Double.toString(value);
    }
    public String pow(double firstNumber, double lastNumber){
        double value = Math.pow(firstNumber, lastNumber);

        return Double.toString(value);
    }
}
