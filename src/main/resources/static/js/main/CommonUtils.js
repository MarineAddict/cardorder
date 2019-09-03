var findTabAndActive = function (activeId,activeName,url) {
    var item = {'id':activeId,'name':activeName,'url':url,'closable':true,'method':'post'};
    closableTab.addTab(item);
};

var closeTab=function(tabId){
    var item={"tabclose":"tab_seed_"+tabId};
    closableTab.closeTab(item);
}


 var callOption = function (e) {
     $.ajax({
         type:'get',
         url:'/option/getOptions' ,
         success:function(response) {
             var data=response.data;
             if (data != null) {
                 localStorage.clear();
                 data.forEach(function (item) {
                     localStorage.setItem(item.key,JSON.stringify(item.optionList)); //存入 参数： 1.调用的值 2.所要存入的数据
                 });
             }else{
                 alert("加载option有误");
             }
         }
     })
 }

 var getOption=function(key){
     if(key==null){
         return;
     }
     var option=localStorage.getItem(key);
     if(option!=null){
         return JSON.parse(option);
     }
}


