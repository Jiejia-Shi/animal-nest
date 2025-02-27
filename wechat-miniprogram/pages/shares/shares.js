// pages/shares/shares.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    shareList: [],
    page: 1,
    pageSize: 5,
    total: 0,
    isloading: false,
    insertId: null,
    deleteID: null,
    changeId: null,
    changeNumber: null,
    swiperList: ["/pictures/association.png", "/pictures/activity.png"] //存放轮播图数据
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getShareList()
  },


  btnGoToAssociation() {
    wx.navigateTo({
      url: '/pages/association/association',
    })
  },

  btnGoToActivity() {
    wx.navigateTo({
      url: '/pages/activity/activity',
    })
  },

  getShareList() {
    this.setData({
      isloading: true
    })
    wx.showLoading({
      title: '数据加载中',
    })

    wx.request({
      url:'http://'+app.globalData.url+'/posting/postingInfo',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
        //如果是get请求：'Content-Type': 'application/json'
      },
      data: {
        page : this.data.page,
        pageSize : this.data.pageSize,
        userId: app.globalData.id
      },
      success:(res)=>{
        if(res.data.code == 1) {
          this.setData({
            shareList: [...this.data.shareList, ...res.data.data]
          })
        }
        else {
          wx.showToast({
            title: '没有更多分享了',
          })
        }
      },  
      complete:()=> {
        wx.hideLoading()
        this.setData({
          isloading: false
        })
      }

    })
  },

  btnGoToComment: function(e) {
    var index = e.currentTarget.dataset['index'];
    wx.navigateTo({
      url:"/pages/comment/comment?index=" + index,
    })
  },

  btnThumb: function(e) {
    const index = e.currentTarget.dataset['index']
    for(var i = 0; i < this.data.shareList.length; i++) {
      if(this.data.shareList[i].id == index) {
        if(this.data.shareList[i].like) {
          let likeChange = "shareList[" + i + "].like"
          let likesNumberChange = "shareList[" + i + "].likesNumber"
          this.setData({
            [likeChange]: false,
            [likesNumberChange]: this.data.shareList[i].likesNumber - 1,
            deleteId: this.data.shareList[i].id,
            changeId: this.data.shareList[i].id,
            changeNumber: -1
          })
          this.deleteLike()
          this.changeLikesNumber()
        }
        else {
          let likeChange = "shareList[" + i + "].like"
          let likesNumberChange = "shareList[" + i + "].likesNumber"
          this.setData({
            [likeChange]: true,
            [likesNumberChange]: this.data.shareList[i].likesNumber + 1,
            insertId: this.data.shareList[i].id,
            changeId: this.data.shareList[i].id,
            changeNumber: 1
          })
          this.insertLike()
          this.changeLikesNumber()
        }
      }
    }
   
  },

  insertLike() {
    wx.request({
      url:'http://'+app.globalData.url+'/like/insert',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        userId: app.globalData.id,
        postingId: this.data.insertId
      },
      success:(res)=>{
        console.log(res);
      }
    })

  },

  deleteLike() {
    wx.request({
      url:'http://'+app.globalData.url+'/like/delete',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        userId: app.globalData.id,
        postingId: this.data.deleteId
      },
      success:(res)=>{
        console.log(res);
      }
    })

  },

  changeLikesNumber() {
    wx.request({
      url:'http://'+app.globalData.url+'/posting/changeLikesNumber',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        postingId: this.data.changeId,
        changeNumber: this.data.changeNumber
      },
      success:(res)=>{
        console.log(res);
      }
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
    if(this.data.isloading) return
    this.setData({
      page: this.data.page + 1
    })

    this.getShareList()

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})