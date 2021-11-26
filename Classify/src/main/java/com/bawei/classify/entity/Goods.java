package com.bawei.classify.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Goods {

        @SerializedName("CategoryId")
        private Integer categoryId;
        @SerializedName("CategoryName")
        private String categoryName;
        @SerializedName("CommissionRate")
        private String commissionRate;
        @SerializedName("CommissionType")
        private String commissionType;
        @SerializedName("CouponAmount")
        private String couponAmount;
        @SerializedName("CouponEndTime")
        private String couponEndTime;
        @SerializedName("CouponId")
        private String couponId;
        @SerializedName("CouponInfo")
        private String couponInfo;
        @SerializedName("CouponRemainCount")
        private Integer couponRemainCount;
        @SerializedName("CouponShareUrl")
        private String couponShareUrl;
        @SerializedName("CouponStartFee")
        private String couponStartFee;
        @SerializedName("CouponStartTime")
        private String couponStartTime;
        @SerializedName("CouponTotalCount")
        private Integer couponTotalCount;
        @SerializedName("IncludeDxjh")
        private String includeDxjh;
        @SerializedName("IncludeMkt")
        private String includeMkt;
        @SerializedName("InfoDxjh")
        private String infoDxjh;
        @SerializedName("ItemDescription")
        private String itemDescription;
        @SerializedName("ItemId")
        private Long itemId;
        @SerializedName("ItemUrl")
        private String itemUrl;
        @SerializedName("JddNum")
        private Integer jddNum;
        @SerializedName("LevelOneCategoryId")
        private Integer levelOneCategoryId;
        @SerializedName("LevelOneCategoryName")
        private String levelOneCategoryName;
        @SerializedName("LockRateEndTime")
        private Integer lockRateEndTime;
        @SerializedName("LockRateStartTime")
        private Integer lockRateStartTime;
        @SerializedName("Nick")
        private String nick;
        @SerializedName("NumIid")
        private Long numIid;
        @SerializedName("PictUrl")
        private String pictUrl;
        @SerializedName("PresaleDeposit")
        private String presaleDeposit;
        @SerializedName("PresaleEndTime")
        private Integer presaleEndTime;
        @SerializedName("PresaleStartTime")
        private Integer presaleStartTime;
        @SerializedName("PresaleTailEndTime")
        private Integer presaleTailEndTime;
        @SerializedName("PresaleTailStartTime")
        private Integer presaleTailStartTime;
        @SerializedName("Provcity")
        private String provcity;
        @SerializedName("RealPostFee")
        private String realPostFee;
        @SerializedName("ReservePrice")
        private String reservePrice;
        @SerializedName("SellNum")
        private Integer sellNum;
        @SerializedName("SellerId")
        private Long sellerId;
        @SerializedName("ShopDsr")
        private Integer shopDsr;
        @SerializedName("ShopTitle")
        private String shopTitle;
        @SerializedName("ShortTitle")
        private String shortTitle;
        @SerializedName("SmallImages")
        private List<String> smallImages;
        @SerializedName("Stock")
        private Integer stock;
        @SerializedName("Title")
        private String title;
        @SerializedName("TkTotalCommi")
        private String tkTotalCommi;
        @SerializedName("TkTotalSales")
        private String tkTotalSales;
        @SerializedName("TotalStock")
        private Integer totalStock;
        @SerializedName("Url")
        private String url;
        @SerializedName("UserType")
        private Integer userType;
        @SerializedName("UvSumPreSale")
        private Integer uvSumPreSale;
        @SerializedName("Volume")
        private Integer volume;
        @SerializedName("WhiteImage")
        private String whiteImage;
        @SerializedName("XId")
        private String xId;
        @SerializedName("ZkFinalPrice")
        private String zkFinalPrice;


        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCommissionRate() {
            return commissionRate;
        }

        public void setCommissionRate(String commissionRate) {
            this.commissionRate = commissionRate;
        }

        public String getCommissionType() {
            return commissionType;
        }

        public void setCommissionType(String commissionType) {
            this.commissionType = commissionType;
        }

        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }

        public String getCouponEndTime() {
            return couponEndTime;
        }

        public void setCouponEndTime(String couponEndTime) {
            this.couponEndTime = couponEndTime;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponInfo() {
            return couponInfo;
        }

        public void setCouponInfo(String couponInfo) {
            this.couponInfo = couponInfo;
        }

        public Integer getCouponRemainCount() {
            return couponRemainCount;
        }

        public void setCouponRemainCount(Integer couponRemainCount) {
            this.couponRemainCount = couponRemainCount;
        }

        public String getCouponShareUrl() {
            return couponShareUrl;
        }

        public void setCouponShareUrl(String couponShareUrl) {
            this.couponShareUrl = couponShareUrl;
        }

        public String getCouponStartFee() {
            return couponStartFee;
        }

        public void setCouponStartFee(String couponStartFee) {
            this.couponStartFee = couponStartFee;
        }

        public String getCouponStartTime() {
            return couponStartTime;
        }

        public void setCouponStartTime(String couponStartTime) {
            this.couponStartTime = couponStartTime;
        }

        public Integer getCouponTotalCount() {
            return couponTotalCount;
        }

        public void setCouponTotalCount(Integer couponTotalCount) {
            this.couponTotalCount = couponTotalCount;
        }

        public String getIncludeDxjh() {
            return includeDxjh;
        }

        public void setIncludeDxjh(String includeDxjh) {
            this.includeDxjh = includeDxjh;
        }

        public String getIncludeMkt() {
            return includeMkt;
        }

        public void setIncludeMkt(String includeMkt) {
            this.includeMkt = includeMkt;
        }

        public String getInfoDxjh() {
            return infoDxjh;
        }

        public void setInfoDxjh(String infoDxjh) {
            this.infoDxjh = infoDxjh;
        }

        public String getItemDescription() {
            return itemDescription;
        }

        public void setItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public String getItemUrl() {
            return itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }

        public Integer getJddNum() {
            return jddNum;
        }

        public void setJddNum(Integer jddNum) {
            this.jddNum = jddNum;
        }

        public Integer getLevelOneCategoryId() {
            return levelOneCategoryId;
        }

        public void setLevelOneCategoryId(Integer levelOneCategoryId) {
            this.levelOneCategoryId = levelOneCategoryId;
        }

        public String getLevelOneCategoryName() {
            return levelOneCategoryName;
        }

        public void setLevelOneCategoryName(String levelOneCategoryName) {
            this.levelOneCategoryName = levelOneCategoryName;
        }

        public Integer getLockRateEndTime() {
            return lockRateEndTime;
        }

        public void setLockRateEndTime(Integer lockRateEndTime) {
            this.lockRateEndTime = lockRateEndTime;
        }

        public Integer getLockRateStartTime() {
            return lockRateStartTime;
        }

        public void setLockRateStartTime(Integer lockRateStartTime) {
            this.lockRateStartTime = lockRateStartTime;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public Long getNumIid() {
            return numIid;
        }

        public void setNumIid(Long numIid) {
            this.numIid = numIid;
        }

        public String getPictUrl() {
            return pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }

        public String getPresaleDeposit() {
            return presaleDeposit;
        }

        public void setPresaleDeposit(String presaleDeposit) {
            this.presaleDeposit = presaleDeposit;
        }

        public Integer getPresaleEndTime() {
            return presaleEndTime;
        }

        public void setPresaleEndTime(Integer presaleEndTime) {
            this.presaleEndTime = presaleEndTime;
        }

        public Integer getPresaleStartTime() {
            return presaleStartTime;
        }

        public void setPresaleStartTime(Integer presaleStartTime) {
            this.presaleStartTime = presaleStartTime;
        }

        public Integer getPresaleTailEndTime() {
            return presaleTailEndTime;
        }

        public void setPresaleTailEndTime(Integer presaleTailEndTime) {
            this.presaleTailEndTime = presaleTailEndTime;
        }

        public Integer getPresaleTailStartTime() {
            return presaleTailStartTime;
        }

        public void setPresaleTailStartTime(Integer presaleTailStartTime) {
            this.presaleTailStartTime = presaleTailStartTime;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getRealPostFee() {
            return realPostFee;
        }

        public void setRealPostFee(String realPostFee) {
            this.realPostFee = realPostFee;
        }

        public String getReservePrice() {
            return reservePrice;
        }

        public void setReservePrice(String reservePrice) {
            this.reservePrice = reservePrice;
        }

        public Integer getSellNum() {
            return sellNum;
        }

        public void setSellNum(Integer sellNum) {
            this.sellNum = sellNum;
        }

        public Long getSellerId() {
            return sellerId;
        }

        public void setSellerId(Long sellerId) {
            this.sellerId = sellerId;
        }

        public Integer getShopDsr() {
            return shopDsr;
        }

        public void setShopDsr(Integer shopDsr) {
            this.shopDsr = shopDsr;
        }

        public String getShopTitle() {
            return shopTitle;
        }

        public void setShopTitle(String shopTitle) {
            this.shopTitle = shopTitle;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public List<String> getSmallImages() {
            return smallImages;
        }

        public void setSmallImages(List<String> smallImages) {
            this.smallImages = smallImages;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTkTotalCommi() {
            return tkTotalCommi;
        }

        public void setTkTotalCommi(String tkTotalCommi) {
            this.tkTotalCommi = tkTotalCommi;
        }

        public String getTkTotalSales() {
            return tkTotalSales;
        }

        public void setTkTotalSales(String tkTotalSales) {
            this.tkTotalSales = tkTotalSales;
        }

        public Integer getTotalStock() {
            return totalStock;
        }

        public void setTotalStock(Integer totalStock) {
            this.totalStock = totalStock;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getUserType() {
            return userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public Integer getUvSumPreSale() {
            return uvSumPreSale;
        }

        public void setUvSumPreSale(Integer uvSumPreSale) {
            this.uvSumPreSale = uvSumPreSale;
        }

        public Integer getVolume() {
            return volume;
        }

        public void setVolume(Integer volume) {
            this.volume = volume;
        }

        public String getWhiteImage() {
            return whiteImage;
        }

        public void setWhiteImage(String whiteImage) {
            this.whiteImage = whiteImage;
        }

        public String getxId() {
            return xId;
        }

        public void setxId(String xId) {
            this.xId = xId;
        }

        public String getZkFinalPrice() {
            return zkFinalPrice;
        }

        public void setZkFinalPrice(String zkFinalPrice) {
            this.zkFinalPrice = zkFinalPrice;
        }

}
