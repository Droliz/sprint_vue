import request from "@/utils/request";

// 获取所有 tag
const getTags = (): Promise<TagsRes> => {
    return request.get('/tags/getTags')
}

export {
    getTags
}