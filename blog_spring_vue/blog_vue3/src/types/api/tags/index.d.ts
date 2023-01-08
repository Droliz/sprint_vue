
interface tagInfo {
    tag_id: number,
    tag: string,
    color: string
}

interface TagsRes extends baseRes{
    data: tagInfo[],
}