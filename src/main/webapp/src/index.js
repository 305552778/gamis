import {loadGoods,showMsg} from "./moduleA";
import imgSource from "../images/logo.png";
//import './style/default.css'

console.log(loadGoods());
const img=document.createElement('img')
img.src=imgSource;
img.style.cssText='width:320px;margin:0 auto;';

const h2=document.createElement('h2');
h2.innerText='这是一个测试';
h2.classList.add('test');

document.body.appendChild(h2);
document.body.appendChild(img);
