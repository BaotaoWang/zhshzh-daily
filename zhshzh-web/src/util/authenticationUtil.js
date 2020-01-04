import {decrypt, encrypt} from '@/util/cryptoUtil'

const TOKEN = 'token'
const TOKEN_KEY = 'e58311d7651252bc'
const TOKEN_IV = 'b84163731f4f502f'
const USERNAME = 'username'
const USERNAME_KEY = 'eab9b81688964b6d'
const USERNAME_IV = '87a0aeac486a87c2'
const PASSWORD = 'password'
const PASSWORD_KEY = '3767e97f3b8945db'
const PASSWORD_IV = 'ab73e4a361b594d6'

/**
 * 将token加密后存入localStorage中
 * @param token token
 */
const saveToken = (token) => {
  // 用crypto算法对token加密
  let encryptedToken = encrypt(token, TOKEN_KEY, TOKEN_IV)
  // 将加密后的token存入localStorage中
  localStorage.setItem(TOKEN, encryptedToken)
}

/**
 * 从localStorage中获取加密后的token，并解密返回
 * @returns {string}
 */
const getToken = () => {
  // 将加密后的token从localStorage中取出
  let encryptedToken = localStorage.getItem(TOKEN)
  // 用crypto算法对token解密并返回
  return decrypt(encryptedToken, TOKEN_KEY, TOKEN_IV)
}

/**
 * 将token从localStorage中移除
 */
const removeToken = () => {
  localStorage.removeItem(TOKEN)
}

/**
 * 将token加密后存入localStorage中
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
 * 从localStorage中获取加密后的token，并解密返回
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

export {
  saveToken,
  getToken,
  removeToken,
  saveUserIdentity,
  getUserIdentity,
  removeUserIdentity
}
