from django.contrib.auth import get_user_model
from rest_framework import serializers

User = get_user_model()

class SignUpSerializer(serializers.ModelSerializer):
    email = serializers.EmailField(required=True, write_only=True)
    password = serializers.CharField(required=True, write_only=True, style={'input_type': 'password'})
    # password2 = serializers.CharField(required=True, write_only=True, style={'input_type': 'password'})

    class Meta(object):
        model = User
        # fields = ('email', 'password', 'password2')
        fields = ('email', 'password',)

    # def validate(self, data):
    #     if data['password'] != data['password2']:
    #         raise serializers.ValidationError({
    #             "password": "패스워드가 불일치합니다."
    #         })
    #     # return super().validate(data)
    #     return data

    # 커스텀 serializer를 사용할 경우 반드시 save함수를 정의해줘야함
    def save(self, request):
        # user = super().save()
        user = User.objects.create(
            email = self.validated_data['email'],
        )
        user.set_password(self.validated_data['password'])
        # user.username = self.validated_data['user_name']
        user.save()
        print(user, request.data, "???????????????????????????????????????????????????")
        print(user.email)
        return user
        # return user





