// pages/newposting/newposting.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    animalId : null,
    words : null,
    picture : '/pictures/camera.png',
    pictureChange : null
  },

  insertPosting() {
    if(this.data.pictureChange != null && this.data.words != null){
      wx.request({
      url:'http://'+app.globalData.url+'/posting/insertPosting',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        picture:this.data.pictureChange,
        words:this.data.words,
        animalId:this.data.animalId,
        userId: app.globalData.id
      },
      success:(res)=>{
        console.log(res);
        wx.showToast({
          title: '发布成功'
        }),
        this.navigate();
      }
    })
  }
    else wx.showToast({
      title: '请填写分享内容并选择分享图片',
      icon:'none'
    })
  },
  
  navigate() {
    setTimeout(()=>
    {
      wx.switchTab({
        url: '/pages/nest/nest',
      })
    }, 2000)
  },

  uploadImage() {
    wx.chooseMedia({
      count: 1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success:(res) => {
        wx.showToast({
          title: '图片加载中',
          icon:'loading',
          duration:2000
        })
        console.log(res.tempFiles[0].tempFilePath)
        console.log(res.tempFiles[0].size)
        this.setData({
          picture: res.tempFiles[0].tempFilePath,
          pictureChange:res.tempFiles[0].tempFilePath
        })
        this.getInfo();
      }
    })
  },

  getInfo() {
    wx.uploadFile({ 
      url: 'https://www.imgurl.ink/api/v2/upload', 
      method:'POST',
      filePath: this.data.picture, 
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
         pictureChange : datalist[3]
       })
      } 
     }) 
  },

  bindTextAreaBlur: function(e) {
    var that = this;
    that.setData({
      words: e.detail.value
    });
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.index);
    this.setData({
      animalId:options.index
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

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