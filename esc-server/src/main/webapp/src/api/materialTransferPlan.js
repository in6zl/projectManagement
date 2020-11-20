import request from '@/utils/request'

export function getList(data,pageNum,pageSize) {
  return request({
    url: '/materialTranSportPlan/list?pageNum='+pageNum+"&pageSize="+pageSize,
    method: 'post',
    data
  })
}
export function save(data) {
  return request({
    url: '/materialTranSportPlan/save',
    method: 'post',
    data
  })
}
export function update(data) {
  return request({
    url: '/materialTranSportPlan/update',
    method: 'post',
    data
  })
}
export function confirm(data) {
  return request({
    url: '/materialTranSportPlan/confirm',
    method: 'post',
    data
  })
}
export function deletePlan(data) {
  return request({
    url: '/materialTranSportPlan/delete/' + data,
    method: 'delete',
  })
}
export function findById(data) {
  return request({
    url: '/materialTranSportPlan/' + data,
    method: 'get',
  })
}
export function confirmList(data) {
  return request({
    url: '/materialTranSportPlan/getList',
    method: 'post',
    data
  })
}
export function duplicateList(data) {
  return request({
    url: '/materialTranSportPlan/list/duplicate',
    method: 'get',
  })
}
//统计待处理，处理中，处理失败，已完成数量
export function statisticsNum(materialTransferList) {
  if(materialTransferList){
    for(let i=0;i<materialTransferList.length;i++){
       let materialTransfer=materialTransferList[i];
      //  let materialTransportPlanSendOrderList=materialTransfer.materialTransportPlanSendOrder;
      //  let unProcessNum=0;
      //  let processingNum=0;
      //  let processFailedNum=0;
      //  let finishedNum=0;
      //  if(materialTransportPlanSendOrderList){
      //     for(let j=0;j<materialTransportPlanSendOrderList.length;j++){
      //        let materialTransportPlanSendOrder=materialTransportPlanSendOrderList[j];
      //        if(materialTransportPlanSendOrder.state==0){
      //         processingNum=processingNum+materialTransportPlanSendOrder.num;
      //        }else if(materialTransportPlanSendOrder.state==1){
      //         finishedNum=finishedNum+materialTransportPlanSendOrder.num;
      //        }else if(materialTransportPlanSendOrder.state==2){
      //         processFailedNum=processFailedNum+materialTransportPlanSendOrder.num;
      //        }
      //     }
      //     unProcessNum=materialTransfer.num-processingNum-finishedNum;
      //  }
       materialTransfer.finishedNum=materialTransfer.finishedNum?materialTransfer.finishedNum:0;
       materialTransfer.unProcessNum=parseFloat(materialTransfer.num) -parseFloat(materialTransfer.finishedNum);
      //  materialTransfer.processingNum=processingNum;
      //  materialTransfer.processFailedNum=processFailedNum;
      
    }
  }
  return materialTransferList;
}