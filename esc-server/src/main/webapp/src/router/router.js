import { routerMap } from "@/router/index";

export function getResources(resources) {
    let newRouter = []
    resources.newForEach(function (item) {
        newRouter.push({
            path: item.url,
            name: item.name,
            redirect: 'noRedirect',
            component: 'Layout',
            meta: {
                title: item.name,
                icon: item.icon
            },
            children: filterChild(item.children || [])
        });
    });
    return newRouter
}
function filterChild(data) {
    let child = [];
    data.newForEach(function (item) {
        child.push({
            path: item.url,
            name: item.name,
            hidden: item.type == 1 ? true : false,
            component: routerMap(item.url),
            meta: {
                title: item.name,
                icon: item.icon
            }
        });
        if(item.children){
          for( let i of item.children){
            child.push({
              path: i.url,
              name: i.name,
              hidden: i.type == 1 ? true : false,
              component: routerMap(i.url),
              meta: {
                title: i.name,
                icon: i.icon
              }
            });
          }

        }
    });
    return child;
}
Array.prototype.newForEach = function(fn) {
    let i
    for(i=0; i<this.length; i++){
       fn(this[i], i)
    }
}
