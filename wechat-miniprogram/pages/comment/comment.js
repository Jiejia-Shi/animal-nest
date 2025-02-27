// pages/comment/comment.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    commentList: [],
    page: 1,
    pageSize: 5,
    isloading: false,
    postingId:null,
    words:null
  },

  
  insertComment() {
    if(this.data.words != null){
      wx.request({
      url:'http://'+app.globalData.url+'/comment/insertComment',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        userId: app.globalData.id,
        postingId:this.data.postingId,
        words:this.data.words
      },
      success:(res)=>{
        console.log(res);
        wx.showToast({
          title: '发布成功'
        })
      }
    })
  }
    else wx.showToast({
      title: '请输入评论内容',
      icon:'none'
    })
  },

  changeText: function(e) {
    var that = this;
    that.setData({
      words: e.detail.value
    });
  },


  getCommentList() {
    this.setData({
      isloading: true
    })
    wx.showLoading({
      title: '数据加载中',
    })

    wx.request({
      url:'http://'+app.globalData.url+'/comment/selectByPostingId',
      method:'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
        //如果是get请求：'Content-Type': 'application/json'
      },
      data: {
        page : this.data.page,
        pageSize : this.data.pageSize,
        postingId : this.data.postingId
      },
      success:(res)=>{
        if(res.data.code == 1) {
          this.setData({
            commentList: [...this.data.commentList, ...res.data.data]
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


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.index);
    this.setData({
      postingId:options.index
    })
    this.getCommentList();
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

    this.getCommentList()

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})