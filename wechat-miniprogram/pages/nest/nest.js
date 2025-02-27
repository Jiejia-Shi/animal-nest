// pages/nest/nest.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    color: null,
    gender: null,
    habit: null,
    habitat: null,
    health: null,
    name: null,
    picture: null,
    temperament: null,
    id: null,
    shareList: [],
    page: 0,
    pageSize: 5,
    isloading: false,
    favorite: false,
    insertId: null,
    deleteID: null,
    changeId: null,
    changeNumber: null
  },

  //分享TA的新鲜事
  newPosting: function(e) {
    var index = e.currentTarget.dataset['index'];
    console.log(index);
    wx.navigateTo({
      url: '/pages/newposting/newposting?index=' + index,
    })
  },
  
  //关心或取消关心动物
  btnFavorite() {
    if(this.data.favorite) {
      this.setData({favorite: false})
      this.deleteFavorite()
      wx.showToast({
        title: '取消关心',
        icon: 'none',
        duration: 1000
      })
    }
    else {
      this.setData({favorite: true})
      this.insertFavorite()
      wx.showToast({
        title: '关心成功,可以在【我的】—【我的关心】”中快速找到TA',
        icon: 'none',
        duration: 2000
      })
    }
  },

  //将关心动物的数据返回到后台
  insertFavorite() {
    wx.request({
      url: 'http://'+app.globalData.url+'/favorite/insert',
      method: 'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        userId: app.globalData.id,
        animalId: this.data.id
      },
      success:(res)=>{
        console.log("关心成功")
      }
    })

  },

  //将删除关心动物的数据返回到后台
  deleteFavorite() {
    wx.request({
      url: 'http://'+app.globalData.url+'/favorite/delete',
      method: 'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data:{
        userId: app.globalData.id,
        animalId: this.data.id
      },
      success:(res)=>{
        console.log("取消成功")
      }
    })

  },

  //下一个动物
  btnNextAnimal() {
    app.globalData.animalNumber = app.globalData.animalNumber + 1
    app.globalData.animalNumber2 = app.globalData.animalNumber
    this.setData({
      page: 0,
      shareList: []
    })
    this.requestAnimalInfo()
  },

  //请求后端动物信息
  requestAnimalInfo() {
    wx.request({
      url: 'http://'+app.globalData.url+'/animal/nextAnimal',
      method: 'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: {
        num: app.globalData.animalNumber,
        userId: app.globalData.id
      },
      success:(res)=>{
        this.setData({
          color: res.data.data.color,
          gender: res.data.data.gender,
          habit: res.data.data.habit,
          habitat: res.data.data.habitat,
          health: res.data.data.health,
          name: res.data.data.name,
          picture: res.data.data.picture,
          temperament: res.data.data.temperament,
          id: res.data.data.id,
          favorite: res.data.data.favorite

        })
        console.log(res)
      }
    })
  },

  //获取收藏的动物的动物信息（从myfavorite跳转而来）
  requestAnimalInfoByFavorite() {
    wx.request({
      url: 'http://'+app.globalData.url+'/animal/favoriteAnimal',
      method: 'POST',
      header:{
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data: {
        animalId: app.globalData.favorite
      },
      success:(res)=>{
        this.setData({
          color: res.data.data.color,
          gender: res.data.data.gender,
          habit: res.data.data.habit,
          habitat: res.data.data.habitat,
          health: res.data.data.health,
          name: res.data.data.name,
          picture: res.data.data.picture,
          temperament: res.data.data.temperament,
          id: res.data.data.id,
          favorite: res.data.data.favorite,
          page: 0

        })
        console.log(res)
      }
    })
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
  },

  //获取该动物的帖子内容
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
        num: app.globalData.animalNumber2,
        userId: app.globalData.id,
        animalId: app.globalData.animalId
      },
      success:(res)=>{
        console.log(res)
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
        if(app.globalData.animalId > 0) app.globalData.animalId = -1
      }

    })
  },

  //点赞或取消点赞
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

  //后端持久化点赞信息
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

  //后端持久化取消点赞信息
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

  //修改点赞的数量
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

  //评论帖子
  btnGoToComment: function(e) {
    var index = e.currentTarget.dataset['index'];
    wx.navigateTo({
      url:"/pages/comment/comment?index=" + index,
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
    //如果favorite标志不为null，则按照myfavorite页面跳转处理
    if(app.globalData.favorite == null) this.requestAnimalInfo()
    else{
      this.requestAnimalInfoByFavorite()
      app.globalData.favorite = null
    } 
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