const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const Dotenv = require('dotenv-webpack');
const mode = process.env.NODE_ENV || 'development';

console.log(`Running in ${mode} mode`);
console.log('Loaded ENV Variables:', process.env);

module.exports = {
    entry: './src/main.tsx',  // El archivo de entrada
    output: {
        path: path.resolve(__dirname, 'dist'), // Directorio de salida
        filename: 'bundle.[contenthash].js', // Agregamos hash para cache busting
        clean: true, // Limpia la carpeta dist en cada build
    },
    mode: mode,//
    module: {
        rules: [
            {
                test: /\.(ts|tsx)$/,  // Soporte para .ts y .tsx
                exclude: /node_modules/,
                use: 'ts-loader',  // Usa ts-loader para TypeScript
            },
            {
                test: /\.(js|jsx)$/,  // Para archivos .js y .jsx
                exclude: /node_modules/,  // No incluir node_modules
                use: {
                    loader: 'babel-loader',  // Usa babel-loader
                },
            },
            {
                test: /\.(css|s[ac]ss)$/i,
                use: [
                    "style-loader",
                    "css-loader",
                    "postcss-loader"
                ],
            }
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html',  // Plantilla HTML
            inject: 'body', // Inyecta el bundle automáticamente
        }),
        new Dotenv({
            path: `./.env.${mode}`
        }),
    ],
    devServer: {
        static: path.join(__dirname, 'dist'),
        compress: true,
        port: 3000,  // Puerto del servidor   
        hot: true, 
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
      },
    },
    resolve: {
        extensions: ['.js', '.jsx', '.ts', '.tsx'],  // Extensiones a resolver
    },
};