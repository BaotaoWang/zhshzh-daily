import {get, post} from '@/http/axios'

export const login = params => post('/login', params)
export const getMenuInfos = params => get('/menuInfos', params)
