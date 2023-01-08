import api from '@/utils/request'

// 获取列表
const getList = (data={}) => {
    return api.post('/user/listPageC1', data)
}

// 新增修改成员
const saveOrMod = (data) => {
    return api.post('/user/saveOrMod', data)
}

// 根据账号查询用户
const findByNo = (no) => {
    return api.get('/user/FindByNo', { params: { no } })
}

// 根据 id 删除
const deleteById = (id) => {
    return api.delete('/user/deleteById', { params: { id } })
}

// 登录
const userLogin = (params) => {
    return api.get('/user/login', {params})
}

export {
    getList,
    saveOrMod,
    findByNo,
    deleteById,
    userLogin
}