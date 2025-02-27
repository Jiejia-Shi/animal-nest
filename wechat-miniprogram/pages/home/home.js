// pages/home/home.js
var app = getApp();


Page({

  /**
   * 页面的初始数据
   */
  data: {
    profile:null,
    name:null,
    pic:null
  },

  //更换头像，选择图片
  uploadImage() {
    wx.chooseMedia({
      count: 1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success:(res) => {
        console.log(res.tempFiles[0].tempFilePath)
        console.log(res.tempFiles[0].size)
        this.setData({
          profile: res.tempFiles[0].tempFilePath,
          pic:res.tempFiles[0].tempFilePath
        })
        console.log(this.data.profile)
        this.getInfo();
      }
    })
  },

  //图像上传到图床
  getInfo() {
    wx.uploadFile({ 
      url: 'https://www.imgurl.ink/api/v2/upload', 
      method:'POST',
      filePath: this.data.profile, 
      name: 'file', 
      formData:{ 
      'uid': 'ecfc51dcbf6d45b178150dd7b0263dea',
      'token': 'd1fb1fe6f301152c11702406308af91f'
      }, 
      complete: (res) =>{ 
       console.log(res)
       var anslist = res.data.split(",")
       var datalist = anslist[3].split("\"")
       this.setData({
         pic : datalist[3]
       })
      } 
     }) 
  },

  //后端更新图片连接
  changeProfile() {
    wx.request({
      url:'http://'+app.globalData.url+'/wxlogin/updateUserProfile',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        id: app.globalData.id,
        profile : this.data.pic
      },
      success:(res)=>{
        console.log(res);
      }
    })
  },

  //更换用户名之后端同步
  changeName() {
    wx.request({
      url:'http://'+app.globalData.url+'/wxlogin/updateUserName',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        id: app.globalData.id,
        name: this.data.name
      },
      success:(res)=>{
        console.log(res);
      }
    })
  },

  //更换用户名之输入框文字存储
  bindTextAreaBlur: function(e) {
    console.log(e.detail.value);
    var that = this;
    if(that.data.name != null)
    that.setData({
    name: e.detail.value
    }); 
  },


  

  //【我的发布】按钮
  btnmp() {
    wx.navigateTo({
      url: '/pages/myposting/myposting',
    })
  },

  //【我的关心】按钮
  btnmf() {
    wx.navigateTo({
      url: '/pages/myfavorite/myfavorite',
    })
  },

  //【我要反馈】按钮
  btnfb() {
    wx.navigateTo({
      url: '/pages/feedback/feedback'
    })
  },

  /*onChooseAvatar(e) {
    console.log(e.detail.avatarUrl)
  },*/

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    //将用户的头像、姓名展示到此页面
    setTimeout( ()=>{
      this.setData({
        profile: app.globalData.profile,
        name: app.globalData.name
      })
    }, 2000)


  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    if(this.data.name != null) this.changeName();
    if(this.data.pic != null) this.changeProfile();
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})