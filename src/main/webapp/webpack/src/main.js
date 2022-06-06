
var x=new Vue(
    {
        el:'#test',
        data:{
            message:'这就是VUE',
            click:0
        },
        method:{
            test:function () {
                this.click++;
                this.message="你点击了"+this.click+"次，在点5次可能会中奖~~";
            }
        }

    }
)