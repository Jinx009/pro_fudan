/**
 * Created by jinx on 10/26/16.
 */
/**
 * 从本地存储中获取元素
 * @param key
 * @returns {null}
 */
function getStorage(key){
    if(window.localStorage) {
        return window.localStorage.getItem(key);
    }else {
        return null;
    }
}
/**
 * 设置本地存储元素
 * @param key
 * @param value
 */
function setStorage(key,value){
    if(window.localStorage) {
        window.localStorage.setItem(key,value);
    }
}
/**
 * 从本地存储中移除一个元素
 * @param key
 */
function removeStorage(key){
    if(window.localStorage){
        window.localStorage.removeItem(key);
    }
}

function newUser(userId){
	if(window.localStorage) {
		var status =  getStorage("newStatus");
		if(status!=null&&status==1){
			setStorage("newStatus","0");
			location.reload();
		}
    }
}