# 空文字
empty_string=""
if [[ $empty_string = $undefined_val ]]; then
  echo "equal"
else
  echo "not equal"
fi

# 数字計算（以下はエラー）
# echo $((1 + $undefined_val))

# 配列
array1=(100)
array_empty=()

# 配列要素数
echo ${#undefined_val[*]}

# 配列比較
echo ${array1[@]} ${undefined_val[@]} | tr ' ' '\n' | sort | uniq -u
echo ${array_empty[@]} ${undefined_val[@]} | tr ' ' '\n' | sort | uniq -u
