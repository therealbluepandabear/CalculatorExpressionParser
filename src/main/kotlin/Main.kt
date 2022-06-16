data class MathExpr(val leftHandNumber: Int,
                    val opr: Char,
                    val rightHandNumber: Int) {
    var precedence = 0

    init {
        if (opr == '*') {
            precedence = 1
        }
    }

    fun compute(): Int {
        return when (opr) {
            '-' -> {
                (leftHandNumber - rightHandNumber)
            }

            '+' -> {
                (leftHandNumber + rightHandNumber)
            }

            '*' -> {
                (leftHandNumber * rightHandNumber)
            }

            else -> {
                (leftHandNumber / rightHandNumber)
            }
        }
    }
}

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

        if (char == '-' || char == '+' || char == '/' || char == '*') {
            if (exprList.isEmpty() && curLeftHandNumber != "" && curOpr == ' ') {
                curOpr = char
            } else if (exprList.isEmpty() && curLeftHandNumber != "" && curRightHandNumber != "") {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))

                curRightHandNumber = ""
                curOpr = char
                curLeftHandNumber = exprList.first().compute().toString()
            } else {
                exprList.add(MathExpr(curLeftHandNumber.toInt(), curOpr, curRightHandNumber.toInt()))
                curLeftHandNumber = exprList[exprList.size - 1].compute().toString()

                curOpr = char
                curRightHandNumber = ""
            }
        }

        if (index == expression.length - 1) {
            val result = when (curOpr) {
                '-' -> {
                    curLeftHandNumber.toInt() - curRightHandNumber.toInt()
                }

                '+' -> {
                    curLeftHandNumber.toInt() + curRightHandNumber.toInt()
                }

                '*' -> {
                    curLeftHandNumber.toInt() * curRightHandNumber.toInt()
                }

                else -> {
                    curLeftHandNumber.toInt() / curRightHandNumber.toInt()
                }
            }

            curLeftHandNumber = result.toString()
        }
    }

    println("result: $curLeftHandNumber") // result
}


fun main() {
    parseExpression("5*5*5*5/5")
}
