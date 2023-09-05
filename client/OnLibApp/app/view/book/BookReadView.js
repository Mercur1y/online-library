Ext.define('OnlibApp.view.book.BookReadView', {
    extend: 'Ext.window.Window',
    id: 'bookreadpanel',
    style: {
        titleAlign: 'center'
    },
    alias: 'widget.bookread',
    autoShow: true,
    closeAction: 'destroy',
    items: [{
        id: 'bookReadPanel',
        xtype: 'panel',
        layout: 'card',
        html: '<div style="width: 1440px;height:900px;margin: 0 auto;text-align: center;background-color: \n' +
            ' #fff;">\n' +
            ' <div id="flipbook" style="background-color: #fff;"></div>\n' +
            '</div>',
        listeners: {
            afterrender: function () {
                getpdf("/content/" + sessionStorage.getItem("path"));
            }
        }
    }]
});

$(document).ready(function () {
    var w = $("#flipbook").outerWidth();
    $("#pageslider").css({"width": w});
});

var pagestr = 1, bid = 1, scale = 1, rotate = 90;

function getpdf(url) {
    var loadingTask = pdfjsLib.getDocument(url)//Get pdf file information
    loadingTask.promise
        .then(function (pdf) {
            //Add fixed div s and canvas based on the total number of pages
            for (var i = 1; i <= pdf.numPages; i++) {
                var id = 'canvaspage' + i;
                var div = document.createElement('div');
                div.innerHTML = '<canvas id="' + id + '" class="page"></canvas>';
                Ext.get('flipbook').appendChild(div);
                setcanvas(i, pdf, id)
            }

            //Call turn
            yepnope({
                test: Modernizr.csstransforms,
                yep: ['./lib/turnjs4/lib/turn.js'],
                nope: ['./lib/turnjs4/lib/turn.html4.min.js'],
                complete: loadApp
            })
        })
}

function setcanvas(i, pdf, id) {
    pdf.getPage(i).then(function (page) {
        var viewport = page.getViewport({scale: scale})
        var canvas = document.getElementById(id)
        var context = canvas.getContext('2d')

        if (viewport.height / viewport.width >= 1.42) {

            var newScale = 1440 / viewport.height
            var newViewport = page.getViewport({scale: newScale, rotation: rotate})
            var outputScale = window.devicePixelRatio

            canvas.width = Math.floor(newViewport.width * outputScale);
            canvas.height = Math.floor(newViewport.height * outputScale);
            canvas.style.width = Math.floor(newViewport.width) + "px";
            canvas.style.height = Math.floor(newViewport.height) + "px";

            var transform = outputScale !== 1 ? [outputScale, 0, 0, outputScale, 0, 0] :
                null;

            var renderContext = {
                canvasContext: context,
                transform: transform,
                viewport: newViewport
            }
            page.render(renderContext)

            return;
        }

        var newScale = 900 / viewport.height

        var newViewport = page.getViewport({scale: newScale})
        var outputScale = window.devicePixelRatio

        canvas.width = Math.floor(newViewport.width * outputScale);
        canvas.height = Math.floor(newViewport.height * outputScale);
        canvas.style.width = Math.floor(newViewport.width) + "px";
        canvas.style.height = Math.floor(newViewport.height) + "px";

        var transform = outputScale !== 1 ? [outputScale, 0, 0, outputScale, 0, 0] :
            null;

        var renderContext = {
            canvasContext: context,
            transform: transform,
            viewport: newViewport
        }
        page.render(renderContext)
    })
}

function loadApp() {
    $("#flipbook").turn({
        width: 1270,
        height: 900,
        elevation: 50,
        autoCenter: true,
        duration: 1000,
    })
}