

function AddScript(url){
    var script = document.createElement('script');
    script.setAttribute('type','text/javascript');
    script.setAttribute('src',url);
    document.getElementsByTagName('head')[0].appendChild(script);
}
AddScript("/js/crypto/core.js");
AddScript("/js/crypto/enc-utf16.js");
//AddScript("/js/crypto/enc-base64.js");
AddScript("/js/crypto/cipher-core.js");
AddScript("/js/crypto/md5.js");
AddScript("/js/crypto/aes.js");
function MD5Encrypt(data)
{
    return CryptoJS.MD5(data).toString();
}

function encrypt(data) {
    let strs=[];
    for(let i in data){
        strs.push(i+'='+data[i]);
    }
    strs.sort();  // 数组排序
    strs=strs.join('&'); // 数组变字符串
    let endData=strs+'&sign='+CryptoJS.MD5(strs+'ADfj3kcadc2349akvm1CPFFCD84f').toString(); // MD5加密
    let key = CryptoJS.enc.Utf8.parse("0984076B18D7EE61"); // 加密秘钥
    let iv = CryptoJS.enc.Utf8.parse("CB3EC842D7C69578");  //  矢量
    let encryptResult = CryptoJS.AES.encrypt(endData,key, {   //  AES加密
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7  // 后台用的是pad.Pkcs5,前台对应为Pkcs7
    });
    return encodeURIComponent(CryptoJS.enc.Base64.stringify(encryptResult.ciphertext));  // Base64加密encode;
}
function decryption(data) {
    let key = CryptoJS.enc.Utf8.parse("0984076B18D7EE61");  // 加密秘钥
    let iv = CryptoJS.enc.Utf8.parse("CB3EC842D7C69578");   //  矢量
    let baseResult=CryptoJS.enc.Base64.parse(data);   // Base64解密
    let ciphertext=CryptoJS.enc.Base64.stringify(baseResult);     // Base64解密
    let decryptResult = CryptoJS.AES.decrypt(ciphertext,key, {    //  AES解密
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    let resData=decryptResult.toString(CryptoJS.enc.Utf8).toString();

    return JSON.parse(resData);
}