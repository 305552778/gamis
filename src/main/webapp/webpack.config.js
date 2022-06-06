const path=require('path');
const htmlWebpackPlugin=require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports={
    mode:'development',
    devtool:'cheap-module-source-map',//生产环境下注释掉此行
    entry:path.join(__dirname,'src','index.js'),
    output:{
        path:path.join(__dirname,'dist'),
        filename:'scripts/[name].[contenthash].bundle.js',//'bundle.js',
        clean:true
        //,assetModuleFilename:'img/[contenthash][ext]'
    },
    plugins:[
        new htmlWebpackPlugin(
            {
                template:path.join(__dirname,'src','index.html'),
                filename:'index.html'
            }
              ,new MiniCssExtractPlugin({
                  filename:'style/[contenthash].css'
              })
        )
    ],
    devServer:{
        port:8000,
        compress:true,
        static:path.join(__dirname,'dist')
    },
    module:{
        rules:[
            {
                test:/\.png$/,
                type:'asset/resource'
                    ,generator:{filename: 'images/[contenthash][ext]'}//编译器优先使用generator
            },
             {
                test:/\.css$/,
                 //use:['style-loader','css-loader']//顺序不能颠倒，执行顺序：从后往前
                 use:[MiniCssExtractPlugin.loader,'css-loader']//顺序不能颠倒，执行顺序：从后往前
             },
            {
                test:/\.(woff|woff2|eot|ttf|otf)$/,
                type:'asset/resource'
            }
        ]
    }
    ,optimization:{
        //splitChunks: {chunks:'all'}
        splitChunks:{
            cacheGroups:{
                vendor:{
                    test:/[\\/]node_modules[\\/]/,//设置缓存第三方库
                    name:'vendors',
                    chunks:'all'
                }
            }
        }
    }
}