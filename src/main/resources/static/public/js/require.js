(function(){
    function load(scripts){
        scripts.split(' ').forEach(function(script){
            document.write('<script src="${path}/public/js/lib/'+script+'.js?'+(+new Date)+'"></scr'+'ipt>')
        }) 
    }
    load('zepto ie detect event touch ajax fx fx_methods');
})()
