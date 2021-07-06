package com.example.calculator

import java.util.Stack

class ArithmeticEvaluation {
    private fun notOperator(char: Char):Boolean=when(char)
    {
        '+','-','/','*'->false
        else->true
    }
    fun evaluation(string: String):Double
    {
        var str=""
        var stack= Stack<Double>()
        for (ch in string)
        {
            if(notOperator(ch) && ch!=' ')
                str+=ch
            else if(ch==' ' && str !="")
            {
                stack.push(str.replace('n','-').toDouble())
                str=""}
            else if(!notOperator(ch))
            {
                var val1=stack.pop()
                var val2=stack.pop()
                when(ch)
                {
                    '+'-> stack.push(val2+val1)
                    '-'-> stack.push(val2- val1)
                    '/'-> stack.push(val2/val1)
                    '*'-> stack.push(val2*val1)
                }
            }
        }
        return stack.pop();
    }
}