import {decrypt, encrypt} from '@/util/cryptoUtil'

/**
 * 为了安全，存在localStorage和sessionStorage中数据的key都为简写，并且反转
 * **_KEY为加密时的密钥，必须为16位十六进制的字符
 * **_IV为加密时的偏移量，必须为16位十六进制的字符
 */
const TOKEN = 'nt'
const TOKEN_KEY = 'e58311d7651252bc'
const TOKEN_IV = 'b84163731f4f502f'
const USERNAME = 'eu'
const USERNAME_KEY = 'eab9b81688964b6d'
const USERNAME_IV = '87a0aeac486a87c2'
const PASSWORD = 'dp'
const PASSWORD_KEY = '3767e97f3b8945db'
const PASSWORD_IV = 'ab73e4a361b594d6'
const AUTO_LOGIN = 'la'
const USER_FULL_NAME = 'nfu'

/**
 * 将token加密后存入sessionStorage中
 * @param token token
 */
const saveToken = (token) => {
  // 用crypto算法对token加密
  let encryptedToken = encrypt(token, TOKEN_KEY, TOKEN_IV)
  // 将加密后的token存入sessionStorage中
  sessionStorage.setItem(TOKEN, encryptedToken)
}

/**
 * 从sessionStorage中获取加密后的token，并解密返回
 * @returns {string}
 */
const getToken = () => {
  // 将加密后的token从sessionStorage中取出
  let encryptedToken = sessionStorage.getItem(TOKEN)
  // 用crypto算法对token解密并返回
  return decrypt(encryptedToken, TOKEN_KEY, TOKEN_IV)
}

/**
 * 将token从sessionStorage中移除
 */
const removeToken = () => {
  sessionStorage.removeItem(TOKEN)
}

/**
 * 将用户姓名存入sessionStorage中
 * @param userFullName 用户姓名
 */
const saveUserFullName = (userFullName) => {
  sessionStorage.setItem(USER_FULL_NAME, userFullName)
}

/**
 * 从sessionStorage中获取并返回用户姓名
 * @returns {string}
 */
const getUserFullName = () => {
  return sessionStorage.getItem(USER_FULL_NAME)
}

/**
 * 将用户姓名从sessionStorage中移除
 */
const removeUserFullName = () => {
  sessionStorage.removeItem(USER_FULL_NAME)
}

/**
 * 将用户身份信息加密后存入localStorage中
 * @param username 用户名
 * @param password 密码
 */
const saveUserIdentity = (username, password) => {
  // 用crypto算法对用户名加密
  let encryptedUsername = encrypt(username, USERNAME_KEY, USERNAME_IV)
  // 将加密后的用户名存入localStorage中
  localStorage.setItem(USERNAME, encryptedUsername)
  // 用crypto算法对密码加密
  let encryptedPassword = encrypt(password, PASSWORD_KEY, PASSWORD_IV)
  // 将加密后的密码存入localStorage中
  localStorage.setItem(PASSWORD, encryptedPassword)
}

/**
 * 从localStorage中获取加密后的用户名和密码，并解密返回
 * @returns {{password: *, username: *}}
 */
const getUserIdentity = () => {
  // 将加密后的用户名从localStorage中取出
  let encryptedUsername = localStorage.getItem(USERNAME)
  // 将加密后的密码从localStorage中取出
  let encryptedPassword = localStorage.getItem(PASSWORD)
  // 用crypto算法对用户名解密
  let username = decrypt(encryptedUsername, USERNAME_KEY, USERNAME_IV)
  // 用crypto算法对密码解密
  let password = decrypt(encryptedPassword, PASSWORD_KEY, PASSWORD_IV)
  return {username: username, password: password}
}

/**
 * 将用户名和密码从localStorage中移除
 */
const removeUserIdentity = () => {
  localStorage.removeItem(USERNAME)
  localStorage.removeItem(PASSWORD)
}

/**
 * 将是否自动登录存入localStorage中
 * @param isAutoLogin 是否自动登录
 */
const saveAutoLoginState = (isAutoLogin) => {
  localStorage.setItem(AUTO_LOGIN, isAutoLogin)
}

/**
 * 从localStorage中获取并返回自动登录状态
 * @returns {boolean}
 */
const getAutoLoginState = () => {
  return localStorage.getItem(AUTO_LOGIN) === 'true'
}

/**
 * 将自动登录状态从localStorage中移除
 */
const removeAutoLoginState = () => {
  localStorage.removeItem(AUTO_LOGIN)
}

export {
  saveToken,
  getToken,
  removeToken,
  saveUserFullName,
  getUserFullName,
  removeUserFullName,
  saveUserIdentity,
  getUserIdentity,
  removeUserIdentity,
  saveAutoLoginState,
  getAutoLoginState,
  removeAutoLoginState
}
