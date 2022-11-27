package com.abizareyhan.core.extension

import android.webkit.WebView

fun WebView.loadInlineHTML(
    html: String
) {
    val bodyHTML = "<div> $html </div>"
    val head =
        "<head><style>" +
                "html{line-height: 1.15; -webkit-text-size-adjust: 100%; }" +
                "body{margin: 0; }" +
                "h1{font-size: 2em;margin: 0.67em 0;}" +
                "pre{font-family: monospace, monospace;font-size: 1em;}" +
                "b,strong{font-weight: bolder;}" +
                "small{font-size: 80%;}" +
                "sub,sup{font-size: 75%;line-height: 0;position: relative;vertical-align: baseline;}" +
                "sub{bottom: -0.25em;}" +
                "sup{top: -0.5em;}" +
                "button,input,optgroup,select,textarea{font-family: inherit;font-size: 100%;line-height: 1.15;margin: 0;}" +
                "img{max-width: 100% !important; width:auto; height: auto !important;}" +
                "</style></head>"

    val dataString = "<html>$head<body>$bodyHTML</body></html>"

    loadData(dataString, "text/html", "UTF-8")
}