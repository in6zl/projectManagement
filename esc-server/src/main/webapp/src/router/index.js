import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

export const viewsMap = {}
viewsMap['Layout'] = Layout
viewsMap['/userManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/userManage')
viewsMap['/editUser'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/editUser')
viewsMap['/addUser'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/userManage/addUser')
viewsMap['/roleManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/roleManage')
viewsMap['/editRole'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/editRole')
viewsMap['/addRole'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/roleManage/addRole')
viewsMap['/organizationManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/organizationManage')
viewsMap['/editOrganization'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/editOrganization')
viewsMap['/addOrganization'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/organizationManage/addOrganization')
viewsMap['/resourceManage'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/resourceManage')
viewsMap['/addResource'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/addResource')
viewsMap['/editResource'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/resourceManage/editResource')
viewsMap['/dictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/dictType')
viewsMap['/editDictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/editDictType')
viewsMap['/addDictType'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/addDictType')
viewsMap['/dict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/dict')
viewsMap['/addDict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/addDict')
viewsMap['/editDict'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/dict/editDict')
viewsMap['/param'] = () => import(/* webpackChunkName: "systemManage" */'@/views/systemManage/param/paramManage')
viewsMap['/productionLineMgr'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productionLine/productionLineMgr/productionLineMgr')
viewsMap['/addProductionLine'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productionLine/productionLineMgr/addProductionLine')
viewsMap['/editProductionLine'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productionLine/productionLineMgr/editProductionLine')
viewsMap['/unit'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/unit/unit')
viewsMap['/addUnit'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/unit/addUnit')
viewsMap['/editUnit'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/unit/editUnit')
viewsMap['/materialMgr'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/materialManager/materialMgr')
viewsMap['/editMaterial'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/materialManager/editMaterial')
viewsMap['/addMaterial'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/materialManager/addMaterial')
viewsMap['/product'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/product')
viewsMap['/editProduct'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/editProduct')
viewsMap['/addProduct'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/productManagement/addProduct')
viewsMap['/warehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/supplierManage/warehouse/warehouse')
viewsMap['/editWarehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/supplierManage/warehouse/editWarehouse')
viewsMap['/addWarehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/supplierManage/warehouse/addWarehouse')
viewsMap['/faWareHouseManage'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseManage/faWareHouseManage')
viewsMap['/addFaWareHouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseManage/addFaWareHouse')
viewsMap['/editFaWareHouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseManage/editFaWareHouse')
viewsMap['/faStorageBinManage'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorageBinManage/faStorageBinManage')
viewsMap['/editFaStorageBin'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorageBinManage/editFaStorageBin')
viewsMap['/addFaStorageBin'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorageBinManage/addFaStorageBin')
viewsMap['/faStorehouseManage'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorehouseManage/faStorehouseManage')
viewsMap['/editFaStorehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorehouseManage/editFaStorehouse')
viewsMap['/printFaStorehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorehouseManage/printFaStorehouse')
viewsMap['/addFaStorehouse'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorehouseManage/addFaStorehouse')
viewsMap['/faWareHouseTagManage'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseTagManage/faWareHouseTagManage')
viewsMap['/addFaWareHouseTag'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseTagManage/addFaWareHouseTag')
viewsMap['/editFaWareHouseTag'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faWareHouseTagManage/editFaWareHouseTag')
viewsMap['/faStorehouseTagRelationship'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/storageManage/faStorehouseManage/faStorehouseTagRelationship')
viewsMap['/workshop'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/workshopManage/workshop')
viewsMap['/addWorkshop'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/workshopManage/addWorkshop')
viewsMap['/editWorkshop'] = () => import(/* webpackChunkName: "basicInfo" */'@/views/workshopManage/editWorkshop')
viewsMap['/supplierOrderDetails'] = () => import(/* webpackChunkName: "storageManage" */'@/views/orderManage/supplierOrder/materialOrder')
viewsMap['/editSupplierOrderDetails'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/supplierOrder/editMaterialOrder')
viewsMap['/addSupplierOrderDetails'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/supplierOrder/addMaterialOrder')
viewsMap['/editSupplierOrder'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/supplierOrder/editMaterialOrder')
viewsMap['/materialPlanList'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/materialRequisition/materialPlanList')
viewsMap['/materialTransfer'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/materialTransferPlan/materialTransfer')
viewsMap['/addMaterialTransfer'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/materialTransferPlan/addMaterialTransfer')
viewsMap['/editMaterialTransfer'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/materialTransferPlan/editMaterialTransfer')
viewsMap['/outboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForMaterial/index')
viewsMap['/editOutboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForMaterial/editAll')
viewsMap['/printOutboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForMaterial/print')
viewsMap['/addOutboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForMaterial/addAll')
viewsMap['/outboundOrderMaterialDetail'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForMaterial/indexDetail')
viewsMap['/inboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForMaterial/index')
viewsMap['/addInboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForMaterial/addAll')
viewsMap['/editInboundOrderMaterial'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForMaterial/editAll')
viewsMap['/inboundOrderMaterialDetail'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForMaterial/indexDetail')
viewsMap['/inboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForProduct/index')
viewsMap['/addInboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForProduct/addAll')
viewsMap['/inboundOrderProductDetail'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForProduct/indexDetail')
viewsMap['/editInboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/inboundForProduct/editAll')
viewsMap['/outboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForProduct/index')
viewsMap['/addOutboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForProduct/addAll')
viewsMap['/outboundOrderProductDetail'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForProduct/indexDetail')
viewsMap['/editOutboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForProduct/editAll')
viewsMap['/printOutboundOrderProduct'] = () => import(/* webpackChunkName: "inOutBoundManage" */'@/views/inOutBoundManage/outboundForProduct/print')
viewsMap['/registrationCode'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/registrationCode/registrationCode')
viewsMap['/discharge'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/registrationCode/discharge')
viewsMap['/productionPlanMgr'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionPlanMgr/productionPlanMgr')
viewsMap['/addProductionPlan'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionPlanMgr/addProductionPlan')
viewsMap['/detailProductionPlan'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionPlanMgr/detailProductionPlan')
viewsMap['/editProductionPlan'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionPlanMgr/editProductionPlan')
viewsMap['/materialRequisitionMgr'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/materialRequisition/materialRequisitionMgr')
viewsMap['/addMaterialRequisition'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/materialRequisition/addMaterialRequisition')
viewsMap['/materialPlanDetail'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/materialRequisition/materialPlanDetail')
viewsMap['/editMaterialRequisition'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/materialRequisition/editMaterialRequisition')
viewsMap['/productionRecord'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionRecord/productionRecord')
viewsMap['/editProductionRecord'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionRecord/editProductionRecord')
viewsMap['/addProductionRecord'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionRecord/addProductionRecord')
viewsMap['/detailProductionRecord'] = () => import(/* webpackChunkName: "productionLine" */'@/views/productionLine/productionRecord/detailProductionRecord')
viewsMap['/inspectionRecord'] = () => import(/* webpackChunkName: "inspectionRecord" */'@/views/inspectionRecord/inspectionRecord')
viewsMap['/editInspectionRecord'] = () => import(/* webpackChunkName: "inspectionRecord" */'@/views/inspectionRecord/editInspectionRecord')
viewsMap['/addInspectionRecord'] = () => import(/* webpackChunkName: "inspectionRecord" */'@/views/inspectionRecord/addInspectionRecord')
viewsMap['/addApplication'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/outProductApplication/addApplication')
viewsMap['/editApplication'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/outProductApplication/editApplication')
viewsMap['/sale'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sale/sale')
viewsMap['/addSale'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sale/add')
viewsMap['/detail'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sale/detail')
viewsMap['/editSale'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sale/editSale')
viewsMap['/deliveryPlan'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/deliveryPlan/deliveryPlan')
viewsMap['/addDeliveryPlan'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/deliveryPlan/addDeliveryPlan')
viewsMap['/editDeliveryPlan'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/deliveryPlan/editDeliveryPlan')
viewsMap['/detailPlan'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/deliveryPlan/detailPlan')
viewsMap['/faOutProductOrder'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/faOutProductOrder/faOutProductOrder')
viewsMap['/editFaOutProductOrder'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/faOutProductOrder/editAll')
viewsMap['/addFaOutProductOrder'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/faOutProductOrder/addAll')
viewsMap['/printOutProductOrder'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/faOutProductOrder/printOutProductOrder')
viewsMap['/outProductApplication'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/outProductApplication/outProductApplication')
viewsMap['/preRegistration'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/preRegistration')
viewsMap['/registration'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/registration/registration')
viewsMap['/editRegistration'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/registration/editRegistration')
viewsMap['/addRegistration'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/factoryManagement/registration/addRegistration')
viewsMap['/car'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/car/index')
viewsMap['/addCar'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/car/add')
viewsMap['/editCar'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/car/edit')
viewsMap['/carSchedule'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/carSchedule/index')
viewsMap['/editCarSchedule'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/carSchedule/edit')
viewsMap['/addCarSchedule'] = () => import(/* webpackChunkName: "factoryManagement" */'@/views/carManage/carSchedule/add')
viewsMap['/supplier'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplier/supplier')
viewsMap['/editSupplier'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplier/editSupplier')
viewsMap['/addSupplier'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplier/addSupplier')
viewsMap['/lookSupplier'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplier/lookSupplier')
viewsMap['/supplierUser'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplierUser/supplierUser')
viewsMap['/editSupplierUser'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplierUser/editSupplierUser')
viewsMap['/addSupplierUser'] = () => import(/* webpackChunkName: "supplierManage" */'@/views/supplierManage/supplierUser/addSupplierUser')
viewsMap['/sendOrder'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sendOrder/sendOrder')
viewsMap['/editSendOrder'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sendOrder/editAll')
viewsMap['/detailSendOrder'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sendOrder/detailSendOrder')
viewsMap['/addSendOrder'] = () => import(/* webpackChunkName: "orderManage" */'@/views/orderManage/sendOrder/addAll')
viewsMap['/supplierMaterialTransfer'] = () => import(/* webpackChunkName: "planManage" */'@/views/planManage/materialTransferPlan/supplierMaterialTransfer')
viewsMap['/productDetail'] = () => import(/* webpackChunkName: "planManage" */'@/views/productManagement/productDetail')

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/3d',
    hidden: true,
    component: () => import('@/views/storageManage/faWareHouseManage/3d.vue'),
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'dashboard', icon: 'home' }
    },
    ]
  },
  // {
  //   path: '*',
  //   redirect: '/404'
  // }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
