// var eventName = ["刷新当前选项卡", "关闭当前选项卡", "关闭其他选项卡", "关闭所有选项卡"];


Vue.directive('tabclose', {
    bind: function (el) {
        var clickItem = document.createElement("ul");
        clickItem.style.width = "150px";
        clickItem.style.border = "1px solid #e4e7ed";
        clickItem.style.position = "absolute";
        clickItem.style.top = "120px";
      
        clickItem.style.zIndex = "4000";
        clickItem.style.background = "#fff";
        clickItem.style.borderRadius = "5px";
        clickItem.style.cursor = "pointer";
        clickItem.style.display = "none";
        clickItem.style.listStyle = "none";
        var item1 = document.createElement("li");
        item1.id = "item1";
        item1.innerHTML = "刷新当前选项卡";
        item1.style.height = "35px";
        item1.style.lineHeight = "35px";
        item1.style.borderBottom = "1px solid  #e2e2e2";
        item1.style.textAlign = "center";
        clickItem.append(item1);
        var item2 = document.createElement("li");
        item2.id = "item2";
        item2.innerHTML = "关闭当前选项卡";
        item2.style.height = "35px";
        item2.style.lineHeight = "35px";
        item2.style.borderBottom = "1px solid  #e2e2e2";
        item2.style.textAlign = "center";
        clickItem.append(item2);


        var item3 = document.createElement("li");
        item3.id = "item3";
        item3.innerHTML = "关闭其他选项卡";
        item3.style.height = "35px";
        item3.style.lineHeight = "35px";
        item3.style.borderBottom = "1px solid  #e2e2e2";
        item3.style.textAlign = "center";
        clickItem.append(item3);


        var item4 = document.createElement("li");
        item4.id = "item4";
        item4.innerHTML = "关闭所有选项卡";
        item4.style.height = "35px";
        item4.style.lineHeight = "35px";
        item4.style.textAlign = "center";
        clickItem.append(item4);
        el.append(clickItem);
        setTimeout(function () {
            document.getElementById("tabs").oncontextmenu = function (event) {
                var ev = event || window.event;
                var mX = event.clientX;
                var mY = event.clientY;
                var w=document.getElementById("aside").offsetWidth;
                clickItem.style.left = (mX-w) + "px";
                clickItem.style.top ="0px";
                clickItem.style.display = "block";
                return false; //取消window自带的菜单弹出来
            };
            document.getElementById("item1").onclick = function () {
                // alert("刷新当前页面");
                var that = app;
                var id = "pane-" + that.currentTap;
                document.getElementById(id).querySelector("iframe").contentWindow.location.reload(true);
                clickItem.style.display = "none";
                // app.refresh();
            };
            document.getElementById("item2").onclick = function () {
                // alert("关闭当前页面");
                var that = app;
                var tabs = app.tabs;
                var activeName = app.currentTap;
                tabs.forEach(function (tab, index) {
                    if (tab.name === activeName) {
                        var nextTab = tabs[index + 1] || tabs[index - 1];
                        if (nextTab) {
                            that.currentTap = nextTab.name;
                        }
                    }
                });
                that.tabs = tabs.filter(function (tab) {
                    return !tab.closable || tab.name !== activeName;
                });
                clickItem.style.display = "none";
                // app.closeCurrent(); //关闭当前
            };
            document.getElementById("item3").onclick = function () {
                // alert("关闭其他页面");
                // app.closeOther(); //关闭其他
                var that = app;
                that.tabs = that.tabs.filter(function (tab) {
                    return tab.name == that.currentTap || !tab.closable;
                });
                clickItem.style.display = "none";
            };
            document.getElementById("item4").onclick = function () {
                // alert("关闭所有页面");
                // app.closeAllPage(); //关闭所有
                var that = app;
                var tab = [];
                that.tabs.forEach(function (item) {
                    if (!item.closable) {
                        tab.push(item);
                    }
                });
                that.tabs = tab;
                if(app.tabs.length>0)  that.currentTap = app.tabs[0].name;
                clickItem.style.display = "none";
            };
            window.addEventListener('click', function () {
                clickItem.style.display = "none";
            });
            // //获取页面有多少个子的iframe 给每个iframe增加点击事件
            // var arr = document.getElementsByClassName("el-tab-pane");
            // console.log(arr.length);
        }, 0);
    }
});