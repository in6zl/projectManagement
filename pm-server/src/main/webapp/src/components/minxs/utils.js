export var utils = {
    data() {
        return {

        }

    },
    methods: {
        debounce(fn, wait) {
            var timeout = null;
            return function () {
                if (timeout !== null) clearTimeout(timeout);
                timeout = setTimeout(fn, wait);
            }
        },
        throttle(fn,wait){
            var pre = Date.now();
            return function(){
                var context = this;
                var args = arguments;
                var now = Date.now();
                if( now - pre >= wait){
                    fn.apply(context,args);
                    pre = Date.now();
                }
            }
        }
    }

}