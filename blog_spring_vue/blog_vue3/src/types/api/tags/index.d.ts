
interface tagInfo {
    tagId: number,
    tag: string,
    color: string
}

interface TagsRes extends baseRes{
    data: tagInfo[],
}