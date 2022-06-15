data class MathExpr(val leftHandNumber: Int,
                    val opr: Char,
                    val rightHandNumber: Int)

private fun parseExpression(expression: String) {
    var curLeftHandNumber = ""
    var curOpr = ' '
    var curRightHandNumber = ""
    val exprList = mutableListOf<MathExpr>()

    for ((index, char) in expression.toCharArray().withIndex()) {
        if (exprList.isEmpty()) {
            if (char.isDigit() &&  curOpr == ' ') {
                curLeftHandNumber += char
            } else if (char.isDigit() && curOpr != ' ') {
                curRightHandNumber += char
            }
        } else {
            if (char.isDigit() && curLeftHandNumber != "") {
                curRightHandNumber += char
            }
        }

        if (char == '-' || char == '+') {
            if (exprList.isEmpty() && curLeftHandNumber != "" && curOpr == ' ') {
                curOpr = char
            } else if (exprList.isEmpty() && curLeftHandNumber != "" && curRightHandNumber != "") {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))
                curRightHandNumber = ""
                curOpr = char
                curLeftHandNumber = if (exprList.first().opr == '-' ) {
                    (exprList.first().leftHandNumber - exprList.first().rightHandNumber).toString()
                } else {
                    (exprList.first().leftHandNumber + exprList.first().rightHandNumber).toString()
                }
            } else {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))
                curLeftHandNumber = if (exprList[exprList.size - 1].opr == '-' ) {
                    (exprList[exprList.size - 1].leftHandNumber - exprList[exprList.size - 1].rightHandNumber).toString()
                } else {
                    (exprList[exprList.size - 1].leftHandNumber + exprList[exprList.size - 1].rightHandNumber).toString()
                }

                curOpr = char
                curRightHandNumber = ""
            }
                println(curLeftHandNumber) // result
                println(exprList)

        }

        if (index == expression.length - 1) {
            val result = if (curOpr == '-') {
                curLeftHandNumber.toInt() - curRightHandNumber.toInt()
            } else {
                curLeftHandNumber.toInt() + curRightHandNumber.toInt()
            }
            curLeftHandNumber = result.toString()
            println(curRightHandNumber)
        }
    }

    println("result" + curLeftHandNumber) // result
}


fun main() {
    parseExpression("1000+33+3+1-99-1000-93983489-23991+1122+3+111+294-8")
}
