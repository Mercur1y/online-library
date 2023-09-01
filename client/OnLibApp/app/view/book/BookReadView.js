Ext.define('OnlibApp.view.book.BookReadView', {
    extend: 'Ext.Panel',
    alias: 'widget.bookread',
    xtype: 'bookRead',
    layout: 'card',
    html: '<div id="flipbook" style=" width:1400px; height:900px;margin-left: 20%;background-color: #fff;"></div></div>',
    listeners: {
        afterrender: function () {getpdf("/content/AmpirV-Viktor_Pielievin.pdf")}
    }
});

var flipbook=Ext.get('flipbook');
var pagestr = 1,bid= 1, scale = 1, rotate = 90;

function getpdf(url){
    var loadingTask=pdfjsLib.getDocument(url)//Get pdf file information
    loadingTask.promise
        .then(function(pdf){
            //Add fixed div s and canvas based on the total number of pages
            for (let i = 1; i <= pdf.numPages;i++) {
                var id = 'canvaspage' + i;
                debugger;
                Ext.DomHelper.append(flipbook, '<canvas id="' + id + '" class="page"></canvas>');
                setcanvas(i,pdf,id)
            }

            //Call turn
            yepnope({
                test : Modernizr.csstransforms,
                yep: ['./lib/turnjs4/lib/turn.js'],
                complete: loadApp
            })
        })
}

function setcanvas(i,pdf,id){
    pdf.getPage(i).then(function(page) {
        var viewport = page.getViewport({scale:scale})
        var canvas = document.getElementById(id)
        var context = canvas.getContext('2d')

        if (viewport.height / viewport.width >= 1.42) {

            var newScale = 1440 / viewport.height
            var newViewport = page.getViewport({scale:newScale,rotation:rotate})
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

            return ;
        }

        var newScale = 900 / viewport.height

        var newViewport = page.getViewport({scale:newScale})
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
        width:1400,
        height:900,
        elevation: 50,
        display: 'single',
        autoCenter: true,
        duration:1000,
        gradients:true,
    })
}