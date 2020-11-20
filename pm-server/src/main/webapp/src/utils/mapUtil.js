import AMap from 'AMap'

let geocoder = new AMap.Geocoder() //地理编码转换


export function createMap(option, locationChangeListener) {
  let location = option.location && option.location instanceof Array ? option.location : null
  
  let map = new AMap.Map(option.el, {
    center: location,
    resizeEnable: true,
    zoom: 18
  })

  initLocationPlugin(map, locationChangeListener, location)
  
  initMapSearch(map, option.searchInput, locationChangeListener)

  //新增Map点击事件
  map.on('click', async (obj) => {
    map.clearMap()
    let addr = await geoDeCode(obj.lnglat.lng, obj.lnglat.lat)
    locationChangeListener({ address: addr, ...obj.lnglat })
    let maker = createMarker(
      [obj.lnglat.lng, obj.lnglat.lat],
      locationChangeListener
    )
    maker.setMap(map)
  })
}




/**
 * 初始化定位插件
 * @param {Map} map 
 */
function initLocationPlugin(map, locationChangeListener, location) {
  AMap.plugin('AMap.Geolocation', function () {
    var geolocation = new AMap.Geolocation({
      // 是否使用高精度定位，默认：true
      enableHighAccuracy: true,
      // 设置定位超时时间，默认：无穷大
      timeout: 10000,
      // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
      buttonOffset: new AMap.Pixel(10, 20),
      //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
      zoomToAccuracy: true,
      //  定位按钮的排放位置,  RB表示右下
      buttonPosition: 'RB'
    })

    map.addControl(geolocation)

    if (location) {
      let marker = createMarker(location, locationChangeListener)
      marker.setMap(map)
    } else {
      geolocation.getCurrentPosition()
    }
    
    AMap.event.addListener(geolocation, 'complete', onComplete)
    AMap.event.addListener(geolocation, 'error', onError)

    function onComplete(data) {
      let { position } = data
      map.clearMap()
      let marker = createMarker([position.lng, position.lat], locationChangeListener)
      marker.setMap(map)
    }

    function onError(data) {
      console.log('定位失败，', data)
    }
  })
}

function initMapSearch(map, searchInput, locationChangeListener) {
  let autoSearch = new AMap.Autocomplete({
    input: searchInput,
  })

  var placeSearch = new AMap.PlaceSearch({
    map: map,
  }) //构造地点查询类

  AMap.event.addListener(autoSearch, 'select', (e) => {
    placeSearch.setCity(e.poi.adcode)
    placeSearch.search(e.poi.name, (status, result) => {
      map.clearMap()
      let pois = result.poiList.pois
      locationChangeListener({ address: e.poi.name, lng: '', lat: '' })
      pois.forEach((item) => {
        let maker = createMarker(item.location, locationChangeListener)
        maker.setMap(map)
      })
      map.setFitView()
    }) //关键字查询查询
  })
}

function createMarker(position, locationChangeListener) {
  let maker = new AMap.Marker({
    position: position,
    draggable: true,
    cursor: 'move',
    raiseOnDrag: true,
  })
  maker.on('mouseup', async (obj) => {
    let addr = await geoDeCode(obj.lnglat.lng, obj.lnglat.lat)
    locationChangeListener({address: addr, ...obj.lnglat})
  })
  return maker
}

function geoDeCode(lng, lat) {
  return new Promise((resolve, reject) => {
    geocoder.getAddress([lng, lat], (status, result) => {
      if (status === 'complete' && result.regeocode) {
        resolve(result.regeocode.formattedAddress)
      } else {
        reject('根据经纬度查询地址失败')
      }
    })
  })
}