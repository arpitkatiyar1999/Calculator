package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var dotValue:Boolean=false
    private lateinit var textView: TextView
    private lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView=findViewById(R.id.textView)
        result=findViewById(R.id.tv_result)
        supportActionBar?.hide()

    }

    fun showView(view: View) {
        if(textView.text.toString()=="")
        {
            textView.append(((view as TextView).text).trim())
            return
        }
        var str=textView.text.last()
        if(str==')')
        {
            textView.append("*"+(view as TextView).text.trim())
            return
        }
        textView.append(((view as TextView).text).trim())
    }

    fun dot(view: View) {
        if(!dotValue) {
            textView.append(".")
            dotValue = true
        }
    }
    fun equal(view: View) {
        dotValue=false
        var string=textView.text.toString()
        if(string!="") {
            var resultString=Model().result(string)
            if(resultString!="Error" && resultString.last()=='0')
                result.text=resultString.substring(0,resultString.length-2)
            else
                result.text=resultString
        }
    }
    fun brackets(view: View)
    {
        if((view as TextView).text=="(" && textView.text.toString()=="")
        {
            textView.append((view).text.trim())
        }
        else if((view).text=="(")
        {
            var str=textView.text.last()
            if(str.isDigit())
                textView.append("*(")
            else
                textView.append("(")
        }
        else if(textView.text.toString()!="" && (view).text==")")
        {
            var openCount=textView.text.toString().filter { it == '(' }.count()
            var closeCount=textView.text.toString().filter { it == ')' }.count()
            if(closeCount<openCount)
                textView.append((view).text.trim())
        }
    }
    fun clearAll(view: View) {
        dotValue=false
        textView.text=""
        result.text="=0"
    }

    fun clearOne(view: View) {

        if(textView.text.toString()=="")
        {
            return
        }
        else if(textView.text.last()=='.')
        {
            textView.text=textView.text.dropLast(1)
            dotValue=false
        }
        else
            textView.text=textView.text.dropLast(1)
    }

    fun operator(view: View) {
        dotValue=false
        if(textView.text.toString()=="" && (view as TextView).text!="-")
        {
            return
        }
        else if(textView.text.toString()=="" && (view as TextView).text=="-")
        {
            textView.append(view.text.trim())
            return
        }
        var str=textView.text.last()
        if((view as TextView).text.toString()=="-" && str!='-')
        {
            textView.append((view).text.trim())
            return
        }
        if (str=='+' || str=='-' || str=='*' || str=='/')
            return
        textView.append((view).text.trim())
    }

}