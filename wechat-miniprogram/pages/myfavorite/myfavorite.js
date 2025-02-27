// pages/myfavorite/myfavorite.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    animalList: []

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getFavoriteList()

  },

  getFavoriteList() {
    wx.request({
      url: 'http://'+app.globalData.url+'/animal/favoriteAnimals',
      method: 'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: {
        userId: app.globalData.id
      },
      success:(res)=>{
        this.setData({
          animalList: res.data.data
        })
      }
    })
  },

  btnFavoriteAnimal: function(e) {
    const index = e.currentTarget.dataset['index']
    app.globalData.favorite = index,
    app.globalData.animalId = index
    wx.switchTab({
      url: '/pages/nest/nest',
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