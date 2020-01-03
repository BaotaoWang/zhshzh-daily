import {post} from '@/http/axios'

export const login = params => post('/login', params)
