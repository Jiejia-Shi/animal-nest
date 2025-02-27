// app.js
App({
  onLaunch() {
    this.login();


    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  
  },

  //检查用户是否登陆过，有缓存
  checkLogin() {
    //this.globalData.openid = wx.getStorageSync('openid')
    this.login()
    this.getUserInfo()
  },

  getUserInfo() {
    wx.request({
      url: "http://"+this.globalData.url+"/wxlogin/getUserInfo",
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: {
        openId: this.globalData.openid
      },
      success: (res)=> {
        this.globalData.id = res.data.id
        this.globalData.profile = res.data.profile
        this.globalData.name = res.data.name
        wx.setStorage({
          key: "openid",
          data: res.data.openId
        })
      }
    })

  },



  // 登录
  login:function(){
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: "http://"+this.globalData.url+"/wxlogin/login",
          method:'POST',
          header:{
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: {
            code: res.code
          },
          success: (res)=> {
            console.log(res);
            this.globalData.openid = res.data.openId
            this.globalData.id = res.data.id
            this.globalData.profile = res.data.profile
            this.globalData.name = res.data.name
            wx.setStorage({
              key: "openid",
              data: res.data.openId
            }),
            this.getUserInfo();
          }
        })
      }
    })
  },


  globalData: {
    userInfo: null,
    openid: null,
    id: null,
    name: null,
    profile: null,
    animalNumber: 1,
    animalNumber2: 1,
    favorite: null,
    animalId: -1,
    url:'47.113.146.182:8080'
    //url:'localhost:8080'
  }
})
