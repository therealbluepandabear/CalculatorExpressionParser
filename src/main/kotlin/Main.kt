
data class MathExpr(val leftHandNumber: Int,
                    val opr: Char,
                    val rightHandNumber: Int)

private fun parseExpression(expression: String) {
    var curLeftHandNumber = ""
    var curOpr = ' '
    var curRightHandNumber = ""
    var exprList = mutableListOf<MathExpr>()

    var sum = 0

    for ((index, char) in expression.toCharArray().withIndex()) {
        if (exprList.isEmpty()) {
            if (char.isDigit() &&  curOpr == ' ' && curRightHandNumber == "") {
                curLeftHandNumber += char
            } else if (char.isDigit() && curOpr != ' ') {
                curRightHandNumber += char
            }
        } else {
            if (char.isDigit() && curLeftHandNumber != "" && curOpr != ' ') {
                curRightHandNumber += char
            }
        }

        if (char == '+') {
            if (exprList.isEmpty() && curLeftHandNumber != "" && curOpr == ' ' && curRightHandNumber == "") {
                curOpr = '+'
            } else if (exprList.isEmpty() && curLeftHandNumber != "" && curRightHandNumber != "" && curOpr !=             ' ') {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))
                curRightHandNumber = ""
                curOpr = '+'
                curLeftHandNumber = (exprList.first().leftHandNumber + exprList.first().rightHandNumber).toString()
            } else {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))
                curLeftHandNumber = (exprList[exprList.size - 1].leftHandNumber + exprList[exprList.size - 1].rightHandNumber).toString()
                curOpr = '+'
                curRightHandNumber = ""
            }
        }

        if (index == expression.length-1 ) {
            var result = curLeftHandNumber.toInt() + curRightHandNumber.toInt()
            curLeftHandNumber = result.toString()
        }
    }

    println(curLeftHandNumber) // result
}


fun main() {
    parseExpression("500+354+23+1+55+999+798+22")
}