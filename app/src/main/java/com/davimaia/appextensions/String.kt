package com.davimaia.appextensions

/*
This one is to make hyperlink on the whole text or you can select one area to do the same thing.
 */
fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = textPaint.linkColor
                textPaint.isUnderlineText = false
                textPaint.typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

//To change only an area in your text just call it and parse these params
makeLinks(
Pair("the text that will be changed", View.OnClickListener {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("the link")
        )
    )
})
)

/*
This one is to brazilian devs that need mask the cpf in any situation
*/
const val CPF_MASK = "*.***.***"

fun toMaskCpf(cpf: String): String {
    val cpfEditable = cpf.substring(2, 11)
    return cpf.replace(cpfEditable, CPF_MASK)
}


/*
 This function calculate age by date 
*/
fun String.calculateAgeByDate(): Int {
    val year = this.substring(0, 4).toInt()
    val month = this.substring(5, 7).toInt()
    val dayOfMonth = this.substring(8, 10).toInt()

    return Period.between(
        LocalDate.of(year, month, dayOfMonth),
        LocalDate.now()
    ).years
}